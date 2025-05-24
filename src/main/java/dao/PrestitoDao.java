package dao;

import entities.ElementoCatalogo;
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
            System.out.println("Il prestito selezionato: " + id + " non è presente nel database");
        }
    }

    // query

    public List<ElementoCatalogo> getByTessera(int tessera) {
        TypedQuery<ElementoCatalogo> query = em.createNamedQuery("Prestito.findByNumeroTessera", ElementoCatalogo.class);
        query.setParameter("numeroTessera", tessera);
        return query.getResultList();
    }

    public List<ElementoCatalogo> getScaduti() {
        TypedQuery<ElementoCatalogo> query = em.createNamedQuery("Prestito.findScaduti", ElementoCatalogo.class);
        return query.getResultList();
    }
}
