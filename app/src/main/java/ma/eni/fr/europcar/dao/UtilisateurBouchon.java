package ma.eni.fr.europcar.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.eni.fr.europcar.model.Agence;
import ma.eni.fr.europcar.model.Utilisateur;

/**
 * Created by Administrateur on 10/04/2018.
 */

public class UtilisateurBouchon implements IUtilisateurDAO
{
    private static UtilisateurBouchon instance;
    private List<Utilisateur> utilisateurs;

    public UtilisateurBouchon()
    {
        this.utilisateurs = new ArrayList<Utilisateur>();
        genererDonnees();
    }

    public static UtilisateurBouchon getInstance()
    {
        if(instance == null)
        {
            instance = new UtilisateurBouchon();
        }

        return instance;
    }

    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur)
    {
        this.utilisateurs.add(utilisateur);
    }

    @Override
    public Utilisateur getUtilisateurAvecId(int id)
    {
        for (Utilisateur utilisateur : this.utilisateurs)
        {
            if(utilisateur.getId().equals(id))
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

    @Override
    public void updateUtilisateur(Utilisateur utilisateur)
    {
        for (int i = 0; i < utilisateurs.size(); i++)
        {
            if(utilisateurs.get(i).getId() == utilisateur.getId())
            {
                utilisateurs.set(i, utilisateur);
                break;
            }
        }
    }

    @Override
    public HashMap<String, String> connexion(Utilisateur utilisateur)
    {
        return null;
    }

    private void genererDonnees()
    {
        Agence agence = new Agence("d", "45", "dfh", 49740, "qsf");
        AgenceBouchon.getInstance().ajouterAgence(agence);
        Utilisateur utilisateur = new Utilisateur("test@gmail.com", "test", "aaa", agence);
        this.utilisateurs.add(utilisateur);
    }
}