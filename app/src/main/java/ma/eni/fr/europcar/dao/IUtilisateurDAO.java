package ma.eni.fr.europcar.dao;

import java.util.List;

import ma.eni.fr.europcar.model.Utilisateur;

/**
 * Created by Administrateur on 10/04/2018.
 */

public interface IUtilisateurDAO
{
    Utilisateur getUtilisateurAvecId(int id);

    Utilisateur getUtilisateurAvecEmail(String email);

    void ajouterUtilisateur(Utilisateur utilisateur);

    List<Utilisateur> getListeDesUtilisateurs();

    void updateUtilisateur(Utilisateur utilisateur);
}