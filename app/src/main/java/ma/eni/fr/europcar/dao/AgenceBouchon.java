package ma.eni.fr.europcar.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.eni.fr.europcar.model.Agence;

/**
 * Created by Administrateur on 10/04/2018.
 */

public class AgenceBouchon implements IAgenceDAO
{
    private static AgenceBouchon instance;
    private List<Agence> agences;

    public AgenceBouchon()
    {
        this.agences = new ArrayList<Agence>();
    }

    public static AgenceBouchon getInstance()
    {
        if(instance == null)
        {
            instance = new AgenceBouchon();
        }

        return instance;
    }

    @Override
    public Agence getAgenceAvecId(String id)
    {
        return null;
    }

    @Override
    public HashMap<String, String> updateAgence(Agence agence, String idUtilisateur) {
        return null;
    }
}