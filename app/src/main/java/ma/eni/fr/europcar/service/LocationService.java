package ma.eni.fr.europcar.service;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.dao.LocationBouchon;
import ma.eni.fr.europcar.model.Location;

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
        return LocationBouchon.getInstance().getLocationById(id);
    }

    public List<Location> getLocationListEnCours() {
        return LocationBouchon.getInstance().getListLocationEnCours();
    }

    public void updateLocation(Location location)
    {
        LocationBouchon.getInstance().updateLocation(location);
    }

    public void reservation(Location location) {

        LocationBouchon.getInstance().reservation(location);

        //Save dans la base SQLITE

        //Appel du server


    }

}
