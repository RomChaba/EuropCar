package ma.eni.fr.europcar.service;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.enums.TypeErreur;
import ma.eni.fr.europcar.model.Utilisateur;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class UtilisateurService
{
    private static UtilisateurService instance;
    private List<Utilisateur> utilisateurs;

    public UtilisateurService()
    {
        this.utilisateurs = new ArrayList<Utilisateur>();
        genererDonnees();
    }

    public UtilisateurService getInstance()
    {
        if(this.instance == null)
        {
            this.instance = new UtilisateurService();
        }

        return this.instance;
    }

    public TypeErreur inscription(String email, String motDePasse)
    {
        if(getUtilisateurAvecEmail(email) != null)
        {
            return TypeErreur.EMAIL_EXISTE_DEJA;
        }
        else
        {
            this.utilisateurs.add(new Utilisateur(email, motDePasse));
        }

        return TypeErreur.OK;
    }

    public TypeErreur connexion(String email, String motDePasse)
    {
        Utilisateur utilisateur = getUtilisateurAvecEmail(email);
        if(utilisateur != null)
        {
            if(!utilisateur.getMotDePasse().equals(motDePasse))
            {
                return TypeErreur.MOT_DE_PASSE_INCORRECT;
            }
        }
        else
        {
            return TypeErreur.EMAIL_EXISTE_PAS;
        }

        return TypeErreur.OK;
    }

    private Utilisateur getUtilisateurAvecEmail(String email)
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

    private void genererDonnees()
    {
        Utilisateur utilisateur = new Utilisateur("test@gmail.com", "test");
        this.utilisateurs.add(utilisateur);
    }
}