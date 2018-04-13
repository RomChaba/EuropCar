package ma.eni.fr.europcar.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.eni.fr.europcar.dao.AgenceBouchon;
import ma.eni.fr.europcar.dao.AgenceHTTP;
import ma.eni.fr.europcar.enums.TypeErreur;
import ma.eni.fr.europcar.model.Agence;
import ma.eni.fr.europcar.model.Utilisateur;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class AgenceService
{
    private Context context;
    private AgenceHTTP agenceHTTP;

    public AgenceService(Context context)
    {
        this.context = context;
        this.agenceHTTP = new AgenceHTTP(this.context);
    }

    public Agence getAgenceAvecId(String id)
    {
        return this.agenceHTTP.getAgenceAvecId(id);
    }

    public HashMap<String, String> updateAgence(Agence agence, String idUtilisateur)
    {
        return this.agenceHTTP.updateAgence(agence, idUtilisateur);
    }
}