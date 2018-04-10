package ma.eni.fr.europcar.service;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.enums.TypeErreur;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Retour;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class RetourService
{
    public static RetourService instance;
    public List<Retour> retours;

    public RetourService()
    {
        this.retours = new ArrayList<Retour>();
    }

    public RetourService getInstance()
    {
        if(this.instance == null)
        {
            this.instance = new RetourService();
        }

        return this.instance;
    }

    public TypeErreur rendre(Retour retour)
    {
        if(isLocationDejaRendu(retour.getLocation()))
        {
            return TypeErreur.LOCATION_DEJA_RENDU;
        }

        this.retours.add(retour);

        return TypeErreur.OK;
    }

    private boolean isLocationDejaRendu(Location location)
    {
        for (Retour retour : this.retours)
        {
            if(retour.getLocation().getId() == location.getId())
            {
                return true;
            }
        }

        return false;
    }
}
