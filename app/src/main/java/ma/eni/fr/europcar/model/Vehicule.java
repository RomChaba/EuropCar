package ma.eni.fr.europcar.model;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class Vehicule {
    private String id;
    private String libelle;
    private int nbPlaces;
    private int locationMin;
    private int locationMax;
    private float tarifMin;
    private float tarifMax;
    private float tarifMoyen;
    private boolean isDisponible;

    public Vehicule() {
    }

    public Vehicule(String id, String libelle, int nbPlaces, int locationMin, int locationMax, float tarifMin, float tarifMax) {
        this.id = id;
        this.libelle = libelle;
        this.nbPlaces = nbPlaces;
        this.locationMin = locationMin;
        this.locationMax = locationMax;
        this.tarifMin = tarifMin;
        this.tarifMax = tarifMax;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public int getLocationMin() {
        return locationMin;
    }

    public void setLocationMin(int locationMin) {
        this.locationMin = locationMin;
    }

    public int getLocationMax() {
        return locationMax;
    }

    public void setLocationMax(int locationMax) {
        this.locationMax = locationMax;
    }

    public float getTarifMin() {
        return tarifMin;
    }

    public void setTarifMin(float tarifMin) {
        this.tarifMin = tarifMin;
    }

    public float getTarifMax() {
        return tarifMax;
    }

    public void setTarifMax(float tarifMax) {
        this.tarifMax = tarifMax;
    }

    public float getTarifMoyen() {
        return tarifMoyen;
    }

    public void setTarifMoyen(float tarifMoyen) {
        this.tarifMoyen = tarifMoyen;
    }

    public boolean isDisponible() {
        return isDisponible;
    }

    public void setDisponible(boolean disponible) {
        isDisponible = disponible;
    }
}
