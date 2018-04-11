package ma.eni.fr.europcar.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.dao.AgenceBouchon;
import ma.eni.fr.europcar.enums.TypeErreur;
import ma.eni.fr.europcar.model.Agence;
import ma.eni.fr.europcar.model.Utilisateur;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class AgenceService
{
    private Context context;

    public AgenceService(Context context)
    {
        this.context = context;
    }

    public TypeErreur ajouter(Agence agence)
    {
        if(AgenceBouchon.getInstance().getAgenceAvecSiret(agence.getSiret()) != null)
        {
            return TypeErreur.SIRET_EXISTE_DEJA;
        }
        else
        {
            AgenceBouchon.getInstance().ajouterAgence(agence);
        }

        return TypeErreur.OK;
    }
}