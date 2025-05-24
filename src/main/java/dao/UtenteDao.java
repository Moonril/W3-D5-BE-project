package dao;

import entities.Utente;
import jakarta.persistence.EntityManager;

public class UtenteDao {
    private EntityManager em;

    public UtenteDao(EntityManager em){
        this.em = em;
    }

    public void save(Utente e){
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public Utente getByTessera(int tessera){
        return em.find(Utente.class, tessera);
    }

    public void remove(int tessera) {
        Utente t = getByTessera(tessera);

        if(t!=null){
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        } else {
            System.out.println("L'utente  con tessera: " + tessera + " non è presente nel database");
        }
    }

}
