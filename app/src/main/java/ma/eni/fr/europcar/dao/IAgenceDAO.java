package ma.eni.fr.europcar.dao;

import java.util.HashMap;

import ma.eni.fr.europcar.model.Agence;

/**
 * Created by Administrateur on 10/04/2018.
 */

public interface IAgenceDAO
{
    Agence getAgenceAvecId(String id);

    HashMap<String, String> updateAgence(Agence agence, String idUtilisateur);
}