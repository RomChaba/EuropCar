package ma.eni.fr.europcar.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.dao.LocationBouchon;
import ma.eni.fr.europcar.dao.VehiculeBouchon;
import ma.eni.fr.europcar.dao.VehiculeHTTP;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Vehicule;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class VehiculeService
{
    private Context context;
    private VehiculeHTTP vehiculeHTTP;

    public VehiculeService(Context context)
    {
        this.context = context;
        this.vehiculeHTTP = new VehiculeHTTP(this.context);
    }

    public List<Vehicule> getListeDesVehiculesDisponibles()
    {
        return this.vehiculeHTTP.getListVehicule();
    }

    public Vehicule getVehiculeById(String id)
    {
        return this.vehiculeHTTP.getVehiculeById(id);
    }
}