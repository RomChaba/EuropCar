package ma.eni.fr.europcar.dao;

import java.util.HashMap;
import java.util.List;

import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Retour;
import ma.eni.fr.europcar.model.Vehicule;

/**
 * Created by Administrateur on 09/04/2018.
 */

public interface ILocationDAO
{


    boolean rendre(Retour rendu);


    //Gestion des Locations
    List<Location> getListLocation(String idAgence);

    List<Location> getListLocationEnCours(String idAgence);

    Location getLocationById(String id);

    HashMap<String, String> reservation(Location location, String idUtilisateur);

    void updateLocation(Location location);

}
