package fr.ensicaen.tennis.bean;

import java.io.Serializable;


public class InscriptionId implements Serializable {
    private Long adherent;
    private Long tournoi;

    public InscriptionId() {
    }

    public InscriptionId(Long adherent, Long tournoi) {
        this.adherent = adherent;
        this.tournoi = tournoi;
    }

    public Long getAdherent() {
        return adherent;
    }

    public void setAdherent(Long adherent) {
        this.adherent = adherent;
    }

    public Long getTournoi() {
        return tournoi;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InscriptionId that = (InscriptionId) o;

        if (!adherent.equals(that.adherent)) return false;
        return tournoi.equals(that.tournoi);
    }

    @Override
    public int hashCode() {
        int result = adherent.hashCode();
        result = 31 * result + tournoi.hashCode();
        return result;
    }

}