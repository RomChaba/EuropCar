package ma.eni.fr.europcar.dao;

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
    List<Location> getListLocation();

    List<Location> getListLocationEnCours();

    Location getLocationById(int id);

    boolean reservation(Location location);

    void updateLocation(Location location);

}
