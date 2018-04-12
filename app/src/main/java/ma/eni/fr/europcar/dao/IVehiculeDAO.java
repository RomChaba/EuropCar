package ma.eni.fr.europcar.dao;

import java.util.List;

import ma.eni.fr.europcar.model.Vehicule;

/**
 * Created by Administrateur on 12/04/2018.
 */

public interface IVehiculeDAO
{
    List<Vehicule> getListVehicule();

    Vehicule getVehiculeById(int id);
}
