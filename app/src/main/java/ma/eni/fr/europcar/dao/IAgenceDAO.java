package ma.eni.fr.europcar.dao;

import ma.eni.fr.europcar.model.Agence;

/**
 * Created by Administrateur on 10/04/2018.
 */

public interface IAgenceDAO
{
    void ajouterAgence(Agence agence);

    Agence getAgenceAvecSiret(String siret);
}