package ma.eni.fr.europcar.dao;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.model.Utilisateur;

/**
 * Created by Administrateur on 10/04/2018.
 */

public class UtilisateurBouchon implements IUtilisateurDAO
{
    private static final UtilisateurBouchon instance = new UtilisateurBouchon();
    List<Utilisateur> utilisateurs;

    public UtilisateurBouchon()
    {
        this.utilisateurs = new ArrayList<Utilisateur>();
    }

    public static UtilisateurBouchon getInstance()
    {
        return instance;
    }

    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur)
    {

    }

    @Override
    public Utilisateur getUtilisateurAvecId(int id)
    {
        for (Utilisateur utilisateur : this.utilisateurs)
        {
            if(utilisateur.getId() == id)
            {
                return utilisateur;
            }
        }

        return null;
    }

    @Override
    public Utilisateur getUtilisateurAvecEmail(String email)
    {
        for (Utilisateur utilisateur : this.utilisateurs)
        {
            if(utilisateur.getEmail().equals(email))
            {
                return utilisateur;
            }
        }

        return null;
    }

    @Override
    public List<Utilisateur> getListeDesUtilisateurs()
    {
        return this.utilisateurs;
    }

    private void genererDonnees()
    {
        Utilisateur utilisateur = new Utilisateur("test@gmail.com", "test", "aaa");
        this.utilisateurs.add(utilisateur);
    }
}