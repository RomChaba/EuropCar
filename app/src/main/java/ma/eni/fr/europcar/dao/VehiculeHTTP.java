package ma.eni.fr.europcar.dao;

import android.content.Context;

import java.util.List;

import ma.eni.fr.europcar.model.Vehicule;

/**
 * Created by Administrateur on 12/04/2018.
 */

public class VehiculeHTTP implements IVehiculeDAO
{
    private Context context;

    public VehiculeHTTP(Context context)
    {
        this.context = context;
    }

    @Override
    public List<Vehicule> getListVehicule()
    {
        return null;
    }

    @Override
    public Vehicule getVehiculeById(int id)
    {
        return null;
    }
}
