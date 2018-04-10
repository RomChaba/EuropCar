package ma.eni.fr.europcar.service;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.model.Vehicule;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class VehiculeService
{
    private static final VehiculeService ourInstance = new VehiculeService();
    private List<Vehicule> vehiculeList = new ArrayList<>();

    private VehiculeService() {
        generationBidon();
    }

    public static VehiculeService getInstance() {
        return ourInstance;
    }

    public List<Vehicule> getListeDesVehiculesLoues()
    {
        return null;
    }

    public List<Vehicule> getListeDesVehiculesDisponibles()
    {
        return vehiculeList;
    }

    private void generationBidon(){
//        Vehicule(int id, String libelle, int nbPlaces, int locationMin, int locationMax, float tarifMin, float tarifMax)
        for (int i = 1; i < 16; i++) {
            vehiculeList.add(new Vehicule(i,"Peugeot 30"+i,i,i,i+5,i+0.5f,(10*i)+0.99f));
        }
    }

    public Vehicule getVehiculeById(int id){

        for (Vehicule veh : vehiculeList) {
            if (veh.getId() == id) {
                return veh;
            }
        }
        return null;
    }

}