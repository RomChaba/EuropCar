package ma.eni.fr.europcar.enums;

/**
 * Created by Administrateur on 09/04/2018.
 */

public enum TypeErreur
{
    OK("Aucune erreur."),
    EMAIL_EXISTE_DEJA("Cette adresse email est déjà utilisée par un autre utilisateur."),
    EMAIL_EXISTE_PAS("Cette adresse email n'existe pas."),
    MOT_DE_PASSE_INCORRECT("Le mot de passe est incorrect."),
    SIRET_EXISTE_DEJA("Ce numéro de SIRET est déjà enregistré.");

    String message;

    TypeErreur(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}