package fr.ensicaen.tennis.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import fr.ensicaen.tennis.bean.Adherent;
import fr.ensicaen.tennis.bean.Inscription;
import fr.ensicaen.tennis.bean.InscriptionId;
import fr.ensicaen.tennis.bean.Tournoi;
import fr.ensicaen.tennis.exception.AlreadyEnrolledException;

import java.time.LocalDate;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class InscriptionDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("tennis");

    private EntityManagerFactory getEMF() {
        return emf;
    }

    public void inscrireAdherent(long idTournoi, long idAdherent) throws AlreadyEnrolledException {
        EntityManager em = getEMF().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Adherent adherent = em.find(Adherent.class, idAdherent);
            Tournoi tournoi = em.find(Tournoi.class, idTournoi);

            if (adherent == null || tournoi == null) {
                throw new IllegalArgumentException("Adherent or Tournoi not found.");
            }

            Inscription existing = em.find(Inscription.class, new InscriptionId(idAdherent, idTournoi));
            if (existing != null) {
                throw new AlreadyEnrolledException("Déjà inscrit à ce tournoi !");
            }

            Inscription inscription = new Inscription();
            inscription.setAdherent(adherent);
            inscription.setTournoi(tournoi);
            inscription.setDateInscription(LocalDate.now());

            em.persist(inscription);
            tx.commit();
        } catch (AlreadyEnrolledException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    public void desinscrireAdherent(long idTournoi, long idAdherent) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
    
        try {
            tx.begin();
            InscriptionId id = new InscriptionId(idAdherent, idTournoi);
            Inscription inscription = em.find(Inscription.class, id);
            if (inscription != null) {
                em.remove(inscription);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
}