package ma.eni.fr.europcar.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.eni.fr.europcar.dao.LocationBouchon;
import ma.eni.fr.europcar.dao.LocationHTTP;
import ma.eni.fr.europcar.model.Location;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class LocationService {
    Context context;
    LocationHTTP locationHTTP;


    public LocationService(Context context) {
        this.context = context;
        this.locationHTTP = new LocationHTTP(context);
    }


    public List<Location> getLocationList(String idAgence) {
        LocationHTTP locationHTTP = new LocationHTTP(context);
        return locationHTTP.getListLocation(idAgence);

//        return LocationBouchon.getInstance().getListLocation();
    }

    public Location getLocationAvecId(String id) {
        LocationHTTP locationHTTP = new LocationHTTP(context);
        return locationHTTP.getLocationById(id);

//        return LocationBouchon.getInstance().getLocationById(id);
    }

    public List<Location> getLocationListEnCours(String idAgence) {
        LocationHTTP locationHTTP = new LocationHTTP(context);
        return locationHTTP.getListLocationEnCours(idAgence);
//        return LocationBouchon.getInstance().getListLocationEnCours();
    }

    public void updateLocation(Location location)
    {
        LocationBouchon.getInstance().updateLocation(location);
    }

    public HashMap<String, String> reservation(Location location, String idUtilisateur) {

//        LocationBouchon.getInstance().reservation(location);

        //Save dans la base SQLITE

        //Appel du server
        return locationHTTP.reservation(location, idUtilisateur);
    }

}
