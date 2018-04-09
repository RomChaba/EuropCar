package ma.eni.fr.europcar.service;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.enums.TypeErreur;
import ma.eni.fr.europcar.model.Agence;
import ma.eni.fr.europcar.model.Utilisateur;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class AgenceService
{
    private static AgenceService instance;
    private List<Agence> agences;

    public AgenceService()
    {
        this.agences = new ArrayList<Agence>();
    }

    public AgenceService getInstance()
    {
        if(this.instance == null)
        {
            this.instance = new AgenceService();
        }

        return this.instance;
    }

    public TypeErreur ajouter(String raisonSociale, String siret, String voie, String codePostal, String ville)
    {
        if(getAgenceAvecSiret(siret) != null)
        {
            return TypeErreur.EMAIL_EXISTE_DEJA;
        }
        else
        {
            this.agences.add(new Agence(raisonSociale, siret, voie, Integer.parseInt(codePostal), ville));
        }

        return TypeErreur.OK;
    }

    private Agence getAgenceAvecSiret(String siret)
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