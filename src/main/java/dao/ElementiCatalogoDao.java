package dao;

import entities.ElementiCatalogo;
import entities.Libro;
import exceptions.ElementoNonTrovatoException;
import jakarta.persistence.*;

import java.util.List;

public class ElementiCatalogoDao {
    private EntityManager em;

    public ElementiCatalogoDao(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        em = emf.createEntityManager();
    }

    public ElementiCatalogoDao(EntityManager em){
        this.em = em;
    }

    public void save(ElementiCatalogo e){
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public ElementiCatalogo getIsbn(String isbn){
        return em.find(ElementiCatalogo.class, isbn);
    }

    public void remove(String isbn) {
        ElementiCatalogo e = getIsbn(isbn);

        if(e!=null){
            em.getTransaction().begin();
            em.remove(e);
            em.getTransaction().commit();
        } else {
            System.out.println("L'elemento " + e + " non è presente nel catalogo");
        }
    }

    //query
    public List<ElementiCatalogo> getByAnno(int anno) {
        TypedQuery<ElementiCatalogo> query = em.createNamedQuery("ElementoCatalogo.findByAnno", ElementiCatalogo.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    public ElementiCatalogo getByIsbn(String isbn) {
        TypedQuery<ElementiCatalogo> query = em.createNamedQuery("ElementoCatalogo.findByIsbn", ElementiCatalogo.class);
        query.setParameter("isbn", isbn);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new  ElementoNonTrovatoException("Nessun elemento trovato con ISBN" + isbn);
        }
    }

    public List<Libro> getByAutore(String autore) {
        TypedQuery<Libro> query = em.createNamedQuery("Libro.findByAutore", Libro.class);
        query.setParameter("autore", autore);
        return query.getResultList(); //?
    }

    public List<ElementiCatalogo> getByTitolo(String titolo){
        TypedQuery<ElementiCatalogo> query = em.createQuery("select e from ElementiCatalogo e where lower(e.titolo) like lower(:titolo", ElementiCatalogo.class);
        query.setParameter("titolo", "%" + titolo + "%");
        return query.getResultList();
    }

}
