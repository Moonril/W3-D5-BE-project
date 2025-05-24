package entities;


import dao.ElementoCatalogoDao;
import dao.PrestitoDao;
import dao.UtenteDao;
import exceptions.ElementoNonTrovatoException;
import jakarta.persistence.EntityManager;

import java.util.*;

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
    private ElementoCatalogoDao elementiDao;
    private PrestitoDao prestitoDao;
    private UtenteDao utenteDao;



    public Archivio(EntityManager em) {
        this.elementiDao = new ElementoCatalogoDao(em);
        this.prestitoDao = new PrestitoDao(em);
        this.utenteDao = new UtenteDao(em);
    }


    //metodi

    public void aggiungiElemento(ElementoCatalogo elemento) {
        elementiDao.save(elemento);
    }

    public void rimuoviElemento(String isbn){
        elementiDao.remove(isbn);
    }

    public ElementoCatalogo cercaPerIsbn (String isbn) {
        return elementiDao.getByIsbn(isbn);
    }

    public List<ElementoCatalogo> cercaPerAnno(int anno) throws ElementoNonTrovatoException {
        List<ElementoCatalogo> lista = elementiDao.getByAnno(anno);
        if (lista.isEmpty()) {
            throw new ElementoNonTrovatoException("Nessun elemento trovato per l'anno " + anno);
        }
        return lista;
    }

    public List<Libro> cercaPerAutore(String autore) throws ElementoNonTrovatoException{
        return elementiDao.getByAutore(autore);
    }

    public List<ElementoCatalogo> cercaPerTitolo(String titolo) {
        return elementiDao.getByTitolo(titolo);
    }

    public List<ElementoCatalogo> getPrestitiAttiviPerUtente(int numeroTessera) {
        return prestitoDao.getByTessera(numeroTessera);
    }

    public List<ElementoCatalogo> getPrestitiScadutiNonRestituiti() {
        return prestitoDao.getScaduti();
    }


}
