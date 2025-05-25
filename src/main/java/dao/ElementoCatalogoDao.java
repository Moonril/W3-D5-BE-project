package dao;

import entities.ElementoCatalogo;
import entities.Libro;
import exceptions.ElementoNonTrovatoException;
import jakarta.persistence.*;

import java.util.List;

public class ElementoCatalogoDao {
    private EntityManager em;

    public ElementoCatalogoDao(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        em = emf.createEntityManager();
    }

    public ElementoCatalogoDao(EntityManager em){
        this.em = em;
    }

    public void save(ElementoCatalogo e){
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public ElementoCatalogo getIsbn(String isbn){
        return em.find(ElementoCatalogo.class, isbn);
    }

    public void remove(String isbn) {
        ElementoCatalogo e = getIsbn(isbn);

        if(e!=null){
            em.getTransaction().begin();
            em.remove(e);
            em.getTransaction().commit();
        } else {
            System.out.println("L'elemento " + e + " non è presente nel catalogo");
        }
    }

    //query
    public List<ElementoCatalogo> getByAnno(int anno) {
        TypedQuery<ElementoCatalogo> query = em.createNamedQuery("ElementoCatalogo.findByAnno", ElementoCatalogo.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    public ElementoCatalogo getByIsbn(String isbn) {
        TypedQuery<ElementoCatalogo> query = em.createNamedQuery("ElementoCatalogo.findByIsbn", ElementoCatalogo.class);
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

    public List<ElementoCatalogo> getByTitolo(String titolo){
        TypedQuery<ElementoCatalogo> query = em.createQuery("select e from ElementoCatalogo e where lower(e.titolo) like lower(:titolo)", ElementoCatalogo.class);
        query.setParameter("titolo", "%" + titolo + "%");
        return query.getResultList();
    }

}
