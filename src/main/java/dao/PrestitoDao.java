package dao;

import entities.ElementiCatalogo;
import entities.Prestito;
import jakarta.persistence.EntityManager;

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

}
