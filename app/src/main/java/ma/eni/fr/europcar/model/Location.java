package ma.eni.fr.europcar.model;

import java.util.Date;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class Location {
    private int id;
    private Date date_debut;
    private Date date_fin;
    private float tarif_journalier;
    private Vehicule vehicule;
    private boolean isEnCours;

    public Location() {
        isEnCours = true;
    }

    public Location(int id, Date date_debut, Date date_fin, float tarif_journalier, Vehicule vehicule, boolean isEnCours) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.tarif_journalier = tarif_journalier;
        this.vehicule = vehicule;
        this.isEnCours = isEnCours;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public float getTarif_journalier() {
        return tarif_journalier;
    }

    public void setTarif_journalier(float tarif_journalier) {
        this.tarif_journalier = tarif_journalier;
    }

    public boolean isEnCours() {
        return isEnCours;
    }

    public void setEnCours(boolean enCours) {
        isEnCours = enCours;
    }
}
