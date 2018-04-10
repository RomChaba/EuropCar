package ma.eni.fr.europcar.dao;

import java.util.ArrayList;
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
    public void ajouterAgence(Agence agence)
    {
        this.agences.add(agence);
    }

    @Override
    public Agence getAgenceAvecSiret(String siret)
    {
        for (Agence agence : this.agences)
        {
            if(agence.getSiret().equals(siret))
            {
                return agence;
            }
        }

        return null;
    }
}