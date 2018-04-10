package ma.eni.fr.europcar.service;

import android.content.Loader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.eni.fr.europcar.model.Agence;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Vehicule;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class LocationService
{
    List<Location> locationList = new ArrayList<>();
    private static final LocationService ourInstance = new LocationService();

    private LocationService() {
        //genererBidon();
    }

    public static LocationService getInstance() {
        return ourInstance;
    }

    public  List<Location> getLocationList(){
        return locationList ;
    }

    public List<Location> getLocationEnCoursList()
    {
        List<Location> locations = new ArrayList<Location>();
        for (Location location : locationList)
        {
            if(location.isEnCours())
            {
                locations.add(location);
            }
        }

        return locations;
    }

    public Location getLocationAvecId(int id)
    {
        for (Location location : this.locationList)
        {
            if(location.getId() == id)
            {
                return location;
            }
        }

        return null;
    }

    public void updateLocation(Location location)
    {
        int i = 0;
        for (Location location2 : locationList)
        {
            if(location.getId() == location.getId())
            {
                locationList.set(i, location);
                break;
            }
            i++;
        }
    }

    public void louer(Vehicule vehicule, Agence agence)
    {
    }

    public void reservation(Vehicule vehicule, Date date_debut, Date date_fin, String tarif_journalier){



        // public Location(int id, Date date_debut, Date date_fin, float tarif_journalier, Vehicule vehicule)
      locationList.add(new Location(locationList.size(),date_debut,date_fin,Float.valueOf(tarif_journalier),vehicule, true));
    }

    private void genererBidon(){
        List<Vehicule> vehiculeList = VehiculeService.getInstance().getListeDesVehiculesDisponibles();


        int compt = 1;
        for (Vehicule test :
                vehiculeList) {
            //Location(int id, Date date_debut, Date date_fin, float tarif_journalier, Vehicule vehicule)
            locationList.add(new Location(compt,new Date(),new Date(2019,2,2),150f,test, true));
            compt++;
        }

    }

}
