package ma.eni.fr.europcar.dao;

import java.util.HashMap;
import java.util.List;

import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Retour;
import ma.eni.fr.europcar.model.Vehicule;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class LocationSQL implements ILocationDAO
{
    @Override
    public HashMap<String, String> reservation(Location location, String idUtilisateur)
    {
        return null;
    }

    @Override
    public void updateLocation(Location location) {

    }

    @Override
    public boolean rendre(Retour rendu)
    {
        return false;
    }

    @Override
    public List<Location> getListLocation(String idAgence) {
        return null;
    }

    @Override
    public List<Location> getListLocationEnCours(String idAgence) {
        return null;
    }

    @Override
    public Location getLocationById(String id) {
        return null;
    }
}
