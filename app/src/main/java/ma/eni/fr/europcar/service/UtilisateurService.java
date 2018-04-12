package ma.eni.fr.europcar.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.eni.fr.europcar.dao.UtilisateurBouchon;
import ma.eni.fr.europcar.dao.UtilisateurHTTP;
import ma.eni.fr.europcar.enums.TypeErreur;
import ma.eni.fr.europcar.model.Utilisateur;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class UtilisateurService
{
    private Context context;
    private UtilisateurHTTP utilisateurHTTP;

    public UtilisateurService(Context context)
    {
        this.context = context;
        this.utilisateurHTTP = new UtilisateurHTTP(this.context);
    }

    public TypeErreur inscription(Utilisateur utilisateur)
    {
        if(UtilisateurBouchon.getInstance().getUtilisateurAvecEmail(utilisateur.getEmail()) != null)
        {
            return TypeErreur.EMAIL_EXISTE_DEJA;
        }
        else
        {
            UtilisateurBouchon.getInstance().ajouterUtilisateur(utilisateur);
        }

        return TypeErreur.OK;
    }

    public HashMap<String, String> connexion(Utilisateur utilisateur)
    {
        return this.utilisateurHTTP.connexion(utilisateur);
    }

    public Utilisateur getUtilisateurAvecEmail(String email)
    {
        return UtilisateurBouchon.getInstance().getUtilisateurAvecEmail(email);
    }

    public void updateUtilisateur(Utilisateur utilisateur)
    {
        UtilisateurBouchon.getInstance().updateUtilisateur(utilisateur);
    }

    public Utilisateur getUtilisateurAvecId(int id)
    {
        return UtilisateurBouchon.getInstance().getUtilisateurAvecId(id);
    }
}