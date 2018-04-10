package ma.eni.fr.europcar.dao;

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
    public boolean reservation(Location location)
    {
        return false;
    }

    @Override
    public boolean rendre(Retour rendu)
    {
        return false;
    }

    @Override
    public List<Vehicule> getListVehicule() {
        return null;
    }

    @Override
    public Vehicule getVehiculeById(int id) {
        return null;
    }

    @Override
    public List<Location> getListLocation() {
        return null;
    }

    @Override
    public Location getLocaionById(int id) {
        return null;
    }
}
