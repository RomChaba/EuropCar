package ma.eni.fr.europcar.service;

import android.content.Loader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.eni.fr.europcar.dao.LocationBouchon;
import ma.eni.fr.europcar.model.Agence;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Vehicule;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class LocationService {
    List<Location> locationList = new ArrayList<>();
    private static final LocationService ourInstance = new LocationService();

    public LocationService() {
    }


    public List<Location> getLocationList() {
        return LocationBouchon.getInstance().getListLocation();
    }

    public Location getLocationAvecId(int id) {
        return LocationBouchon.getInstance().getLocaionById(id);
    }

    public void reservation(Vehicule vehicule, Date date_debut, Date date_fin, String tarif_journalier) {

        LocationBouchon.getInstance().reservation(new Location(locationList.size(), date_debut, date_fin, Float.valueOf(tarif_journalier), vehicule));

        //Save dans la base SQLITE

        //Appel du server


    }

}
