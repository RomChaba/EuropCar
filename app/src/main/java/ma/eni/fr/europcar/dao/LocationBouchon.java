package ma.eni.fr.europcar.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Retour;
import ma.eni.fr.europcar.model.Vehicule;
import ma.eni.fr.europcar.service.VehiculeService;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class LocationBouchon implements ILocationDAO
{
    private static final LocationBouchon ourInstance = new LocationBouchon();

    private List<Vehicule> vehiculeList = new ArrayList<>();
    private List<Location> locationList = new ArrayList<>();

    public static LocationBouchon getInstance() {
        return ourInstance;
    }

    private LocationBouchon() {

        generationVehiculeBidon();
        generationLocationBidon();

    }

    private void generationVehiculeBidon(){
        //Vehicule(int id, String libelle, int nbPlaces, int locationMin, int locationMax, float tarifMin, float tarifMax)
        for (int i = 1; i < 16; i++) {
            this.vehiculeList.add(new Vehicule(i,"Peugeot 30"+i,i,i,i+5,i+0.5f,(10*i)+0.99f));
        }
    }
    private void generationLocationBidon(){
        int compt = 1;
        for (Vehicule test :
                vehiculeList) {
            //Location(int id, Date date_debut, Date date_fin, float tarif_journalier, Vehicule vehicule)
            locationList.add(new Location(compt, new Date(), new Date(2019, 2, 2), 150f, test, true));
            compt++;
        }
    }



    @Override
    public boolean reservation(Location location)
    {
        locationList.add(location);
        return true;
    }

    @Override
    public boolean rendre(Retour rendu)
    {
        return false;
    }

    @Override
    public List<Vehicule> getListVehicule() {

        return vehiculeList;
    }

    @Override
    public Vehicule getVehiculeById(int id) {

        for (Vehicule veh : vehiculeList) {
            if (veh.getId() == id) {
                return veh;
            }
        }
        return null;
    }

    @Override
    public List<Location> getListLocation() {
        return locationList;
    }

    @Override
    public List<Location> getListLocationEnCours() {

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

    @Override
    public Location getLocaionById(int id) {
        for (Location location : this.locationList) {
            if (location.getId() == id) {
                return location;
            }
        }

        return null;
    }

    @Override
    public void updateLocation(Location location)
    {
        for (int i = 0; i < locationList.size(); i++)
        {
            if(locationList.get(i).getId() == location.getId())
            {
                locationList.set(i, location);
                break;
            }
        }
    }
}