package ma.eni.fr.europcar.dao;

import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Retour;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class LocationHTTP implements ILocationDAO
{
    @Override
    public boolean louer(Location location)
    {
        return false;
    }

    @Override
    public boolean rendre(Retour rendu)
    {
        return false;
    }
}
