package entities;


import exceptions.DuplicatoException;
import exceptions.ElementoNonTrovatoException;

import java.util.*;
import java.util.stream.Collectors;


// aggiunta elemento
// ricerca per ISBN
// rimozione per ISBN
// ricerca per anno
// ricerca per autore
// aggiornamento per ISBN
// statistiche

/*
- Aggiunta di un elemento del catalogo
- Rimozione di un elemento del catalogo dato un codice ISBN
- Ricerca per ISBN
- Ricerca per anno pubblicazione
- Ricerca per autore
- Ricerca per titolo o parte di esso
- Ricerca degli elementi attualmente in prestito dato un numero di tessera utente
- Ricerca di tutti i prestiti scaduti e non ancora restituiti
*/
public class Archivio {

    //contenitore
    private Set<ElementiCatalogo> catalogo = new HashSet<>();


    //metodi

    public void aggiungiElemento(ElementiCatalogo elemento) throws DuplicatoException {
        if (catalogo.contains(elemento)) {
            throw new DuplicatoException("Elemento già presente.");
        }
        catalogo.add(elemento);
    }

    public void rimuoviElemento(String isbn){
        catalogo.removeIf(elemento -> elemento.getISBN().equals(isbn));
    }

    public ElementiCatalogo cercaPerIsbn (String isbn) throws ElementoNonTrovatoException {
        return catalogo.stream()
                .filter(elemento -> elemento.getISBN().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new ElementoNonTrovatoException("Elemento non trovato con codice: " + isbn));
    }

    public List<ElementiCatalogo> cercaPerAnno(int anno) throws ElementoNonTrovatoException {
        List<ElementiCatalogo> risultati = catalogo.stream()
                .filter(elemento -> elemento.getAnno() == anno)
                .collect(Collectors.toList());

        if (risultati.isEmpty()) {
            throw new ElementoNonTrovatoException("Nessun elemento trovato per l'anno " + anno);
        }

        return risultati;
    }

    public List<Libro> cercaPerAutore(String autore) throws ElementoNonTrovatoException{
        List<Libro> risultati = catalogo.stream()
                .filter(e -> e instanceof Libro)
                .map(e -> (Libro) e)
                .filter(libro -> libro.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());

        if (risultati.isEmpty()) {
            throw new ElementoNonTrovatoException("Nessun libro trovato per l'autore: " + autore);
        }

        return risultati;
    }

    public void aggiornaElemento(String isbn, ElementiCatalogo nuovoElemento) throws ElementoNonTrovatoException {
        ElementiCatalogo daAggiornare = cercaPerIsbn(isbn);
        catalogo.remove(daAggiornare);
        catalogo.add(nuovoElemento);
    }

    public void stampaStatistiche(){
        Long numeroLibri = catalogo.stream().filter(e->e instanceof Libro).count();
        Long numeroRiviste = catalogo.stream().filter(e->e instanceof Rivista).count();

        Optional<ElementiCatalogo> maxPagine = catalogo.stream()
                .max(Comparator.comparingInt(ElementiCatalogo::getNumeroPagine));

        double mediaPagine = catalogo.stream()
                .mapToInt(ElementiCatalogo::getNumeroPagine)
                .average()
                .orElse(0);

        System.out.println("Totale libri: " + numeroLibri);
        System.out.println("Totale riviste: " + numeroRiviste);
        System.out.println("Elemento con più pagine: " + maxPagine.map(ElementiCatalogo::getTitolo).orElse("Nessuno"));
        System.out.println("Media pagine: " + mediaPagine);
    }

}
