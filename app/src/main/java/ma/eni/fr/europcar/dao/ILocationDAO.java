package ma.eni.fr.europcar.dao;

import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Rendu;

/**
 * Created by Administrateur on 09/04/2018.
 */

public interface ILocationDAO
{
    boolean louer(Location location);

    boolean rendre(Rendu rendu);
}