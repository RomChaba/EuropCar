package ma.eni.fr.europcar.model;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class Utilisateur
{
    private int id;
    private String email;
    private String motDePasse;
    private Agence agence;
    private String token;

    public Utilisateur()
    {

    }

    public Utilisateur(String email, String motDePasse, String token)
    {
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public Utilisateur(int id, String email, String motDePasse, String token)
    {
        this.id = id;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getMotDePasse()
    {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse)
    {
        this.motDePasse = motDePasse;
    }

    public Agence getAgence()
    {
        return agence;
    }

    public void setAgence(Agence agence)
    {
        this.agence = agence;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }
}