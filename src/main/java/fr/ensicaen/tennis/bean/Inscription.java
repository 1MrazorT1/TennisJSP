package fr.ensicaen.tennis.bean;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@IdClass(InscriptionId.class)
public class Inscription {
    @Id
    @ManyToOne
    private Adherent adherent;

    @Id
    @ManyToOne
    private Tournoi tournoi;

    private LocalDate dateInscription;

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    
}
