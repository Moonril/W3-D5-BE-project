package entities;


import dao.ElementiCatalogoDao;
import dao.PrestitoDao;
import dao.UtenteDao;
import exceptions.DuplicatoException;
import exceptions.ElementoNonTrovatoException;

import java.util.*;
import java.util.stream.Collectors;

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
    private ElementiCatalogoDao elementiDao;
    private PrestitoDao prestitoDao;
    private UtenteDao utenteDao;


    //metodi

    public void aggiungiElemento(ElementiCatalogo elemento) {
        elementiDao.save(elemento);
    }

    public void rimuoviElemento(String isbn){
        elementiDao.remove(isbn);
    }

    public ElementiCatalogo cercaPerIsbn (String isbn) {
        return elementiDao.getByIsbn(isbn);
    }

    public List<ElementiCatalogo> cercaPerAnno(int anno) throws ElementoNonTrovatoException {
        return elementiDao.getByAnno(anno);
    }

    public List<Libro> cercaPerAutore(String autore) throws ElementoNonTrovatoException{
        return elementiDao.getByAutore(autore);
    }

    public List<ElementiCatalogo> cercaPerTitolo(String titolo) {
        return elementiDao.getByTitolo(titolo);
    }

    public List<ElementiCatalogo> getPrestitiAttiviPerUtente(int numeroTessera) {
        return prestitoDao.getByTessera(numeroTessera);
    }

    public List<ElementiCatalogo> getPrestitiScadutiNonRestituiti() {
        return prestitoDao.getScaduti();
    }


}
