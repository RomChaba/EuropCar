package ma.eni.fr.europcar.service;

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
        genererBidon();
    }

    public static LocationService getInstance() {
        return ourInstance;
    }

    public  List<Location> getLocationList(){
        return locationList ;
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

    public void louer(Vehicule vehicule, Agence agence)
    {
    }

    private void genererBidon(){
        List<Vehicule> vehiculeList = VehiculeService.getInstance().getListeDesVehiculesDisponibles();


        int compt = 1;
        for (Vehicule test :
                vehiculeList) {
            //Location(int id, Date date_debut, Date date_fin, float tarif_journalier, Vehicule vehicule)
            locationList.add(new Location(compt,new Date(),new Date(2019,2,2),150f,test));
            compt++;
        }

    }

}
