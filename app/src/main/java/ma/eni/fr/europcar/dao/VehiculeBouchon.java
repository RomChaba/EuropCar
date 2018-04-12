package ma.eni.fr.europcar.dao;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.model.Vehicule;

/**
 * Created by Administrateur on 12/04/2018.
 */

public class VehiculeBouchon implements IVehiculeDAO
{
    private List<Vehicule> vehiculeList;
    private static final VehiculeBouchon instance = new VehiculeBouchon();

    public VehiculeBouchon()
    {
        this.vehiculeList = new ArrayList<>();
        generationVehiculeBidon();
    }

    @Override
    public List<Vehicule> getListVehicule()
    {
        return this.vehiculeList;
    }

    public static VehiculeBouchon getInstance()
    {
        return instance;
    }

    @Override
    public Vehicule getVehiculeById(int id)
    {
        for (Vehicule veh : vehiculeList)
        {
            if (veh.getId() == id)
            {
                return veh;
            }
        }
        return null;
    }

    private void generationVehiculeBidon(){
        //Vehicule(int id, String libelle, int nbPlaces, int locationMin, int locationMax, float tarifMin, float tarifMax)
        for (int i = 1; i < 16; i++) {
            this.vehiculeList.add(new Vehicule(i,"Peugeot 30"+i,i,i,i+5,i+0.5f,(10*i)+0.99f));
        }
    }
}
