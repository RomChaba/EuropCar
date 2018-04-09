package ma.eni.fr.europcar.model;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class Agence
{
    private int id;
    private String raisonSociale;
    private String siret;
    private String voie;
    private int codePostal;
    private String ville;

    public Agence()
    {

    }

    public Agence(String raisonSociale, String siret, String voie, int codePostal, String ville)
    {
        this.raisonSociale = raisonSociale;
        this.siret = siret;
        this.voie = voie;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Agence(int id, String raisonSociale, String siret, String voie, int codePostal, String ville)
    {
        this.id = id;
        this.raisonSociale = raisonSociale;
        this.siret = siret;
        this.voie = voie;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getRaisonSociale()
    {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale)
    {
        this.raisonSociale = raisonSociale;
    }

    public String getSiret()
    {
        return siret;
    }

    public void setSiret(String siret)
    {
        this.siret = siret;
    }

    public String getVoie()
    {
        return voie;
    }

    public void setVoie(String voie)
    {
        this.voie = voie;
    }

    public int getCodePostal()
    {
        return codePostal;
    }

    public void setCodePostal(int codePostal)
    {
        this.codePostal = codePostal;
    }

    public String getVille()
    {
        return ville;
    }

    public void setVille(String ville)
    {
        this.ville = ville;
    }
}