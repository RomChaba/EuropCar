package ma.eni.fr.europcar.model;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class Utilisateur
{
    private int id;
    private String email;
    private String motDePasse;

    public Utilisateur()
    {

    }

    public Utilisateur(String email, String motDePasse)
    {
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public Utilisateur(int id, String email, String motDePasse)
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
}