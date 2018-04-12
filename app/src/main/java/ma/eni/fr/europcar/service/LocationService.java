package ma.eni.fr.europcar.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.dao.LocationBouchon;
import ma.eni.fr.europcar.dao.LocationHTTP;
import ma.eni.fr.europcar.model.Location;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class LocationService {
    Context context;


    public LocationService(Context context) {
        this.context = context;
    }


    public List<Location> getLocationList() {
        LocationHTTP locationHTTP = new LocationHTTP(context);
        return locationHTTP.getListLocation();

//        return LocationBouchon.getInstance().getListLocation();
    }

    public Location getLocationAvecId(String id) {
        LocationHTTP locationHTTP = new LocationHTTP(context);
        return locationHTTP.getLocationById(id);

//        return LocationBouchon.getInstance().getLocationById(id);
    }

    public List<Location> getLocationListEnCours() {
        LocationHTTP locationHTTP = new LocationHTTP(context);
        return locationHTTP.getListLocationEnCours();
//        return LocationBouchon.getInstance().getListLocationEnCours();
    }

    public void updateLocation(Location location)
    {
        LocationBouchon.getInstance().updateLocation(location);
    }

    public void reservation(Location location) {

//        LocationBouchon.getInstance().reservation(location);

        //Save dans la base SQLITE

        //Appel du server
        LocationHTTP locationHTTP = new LocationHTTP(context);
        locationHTTP.reservation(location);


    }

}
