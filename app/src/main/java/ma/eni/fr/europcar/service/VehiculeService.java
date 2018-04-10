package ma.eni.fr.europcar.service;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.dao.LocationBouchon;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Vehicule;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class VehiculeService
{

    public VehiculeService() {
    }


    public List<Vehicule> getListeDesVehiculesLoues()
    {
        return null;
    }

    public List<Vehicule> getListeDesVehiculesDisponibles()
    {
        return LocationBouchon.getInstance().getListVehicule();
    }


    public Vehicule getVehiculeById(int id){
        return LocationBouchon.getInstance().getVehiculeById(id);
    }

}