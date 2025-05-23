package dao;

import entities.ElementiCatalogo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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

    public ElementiCatalogo getByIsbn(String isbn){
        return em.find(ElementiCatalogo.class, isbn);
    }

    public void remove(String isbn) {
        ElementiCatalogo e = getByIsbn(isbn);

        if(e!=null){
            em.getTransaction().begin();
            em.remove(e);
            em.getTransaction().commit();
        } else {
            System.out.println("L'elemento " + e + " non è presente nel catalogo");
        }
    }

}
