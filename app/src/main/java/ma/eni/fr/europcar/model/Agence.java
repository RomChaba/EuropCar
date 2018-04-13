package ma.eni.fr.europcar.model;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class Agence
{
    private String id;
    private String raisonSociale;
    private String siret;
    private String voie;
    private String codePostal;
    private String ville;

    public Agence()
    {

    }

    public Agence(String raisonSociale, String siret, String voie, String codePostal, String ville)
    {
        this.raisonSociale = raisonSociale;
        this.siret = siret;
        this.voie = voie;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Agence(String id, String raisonSociale, String siret, String voie, String codePostal, String ville)
    {
        this.id = id;
        this.raisonSociale = raisonSociale;
        this.siret = siret;
        this.voie = voie;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
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

    public String getCodePostal()
    {
        return codePostal;
    }

    public void setCodePostal(String codePostal)
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