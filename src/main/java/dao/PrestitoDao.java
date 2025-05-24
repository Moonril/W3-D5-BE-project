package dao;

import entities.ElementiCatalogo;
import entities.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PrestitoDao {
    private EntityManager em;

    public PrestitoDao(EntityManager em){
        this.em = em;
    }

    public void save(Prestito p){
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public Prestito getById(int id){
        return em.find(Prestito.class, id);
    }

    public void remove(int id) {
        Prestito p = getById(id);

        if(p!=null){
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } else {
            System.out.println("Il prestito selezionato: " + p + " non è presente nel database");
        }
    }

    // query

    public List<ElementiCatalogo> getByTessera(int tessera) {
        TypedQuery<ElementiCatalogo> query = em.createNamedQuery("Prestito.findByNumeroTessera", ElementiCatalogo.class);
        query.setParameter("tessera", tessera);
        return query.getResultList();
    }

    public List<ElementiCatalogo> getScaduti() {
        TypedQuery<ElementiCatalogo> query = em.createNamedQuery("Prestito.findElementiScaduti", ElementiCatalogo.class);
        return query.getResultList();
    }
}
