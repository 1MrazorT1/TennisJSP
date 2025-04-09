package fr.ensicaen.tennis.persistence;

import fr.ensicaen.tennis.bean.Adherent;
import fr.ensicaen.tennis.bean.Tournoi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class TournoiDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("tennis");

    public List<Tournoi> getAllTournois() {
        EntityManager em = emf.createEntityManager();
        List<Tournoi> list = em.createQuery("SELECT t FROM Tournoi t", Tournoi.class).getResultList();
        em.close();
        return list;
    }

    public Tournoi findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Tournoi t = em.find(Tournoi.class, id);
        em.close();
        return t;
    }

    public void save(Tournoi tournoi) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(tournoi);
        em.getTransaction().commit();
        em.close();
    }
    public List<Tournoi> getTournoisInscrits(Adherent adherent) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT i.tournoi FROM Inscription i WHERE i.adherent = :adherent", 
                    Tournoi.class)
                .setParameter("adherent", adherent)
                .getResultList();
        } finally {
            em.close();
        }
    }

}
