package ma.eni.fr.europcar.service;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.dao.UtilisateurBouchon;
import ma.eni.fr.europcar.enums.TypeErreur;
import ma.eni.fr.europcar.model.Utilisateur;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class UtilisateurService
{
    private Context context;

    public UtilisateurService(Context context)
    {

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

    public TypeErreur connexion(Utilisateur utilisateur)
    {
        Utilisateur utilisateur2 = UtilisateurBouchon.getInstance().getUtilisateurAvecEmail(utilisateur.getEmail());
        if(utilisateur2 != null)
        {
            if(!utilisateur.getMotDePasse().equals(utilisateur2.getMotDePasse()))
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
}