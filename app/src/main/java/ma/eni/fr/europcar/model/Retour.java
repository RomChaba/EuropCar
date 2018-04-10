package ma.eni.fr.europcar.model;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class Retour
{
    private int id;
    private Location location;
    private boolean isEndommage;
    private boolean isPleinEffectue;
    private int nbKmsEffectues;
    private String photo;

    public Retour()
    {

    }

    public Retour(Location location, boolean isEndommage, boolean isPleinEffectue, int nbKmsEffectues, String photo)
    {
        this.location = location;
        this.isEndommage = isEndommage;
        this.isPleinEffectue = isPleinEffectue;
        this.nbKmsEffectues = nbKmsEffectues;
        this.photo = photo;
    }

    public Retour(int id, Location location, boolean isEndommage, boolean isPleinEffectue, int nbKmsEffectues, String photo)
    {
        this.id = id;
        this.location = location;
        this.isEndommage = isEndommage;
        this.isPleinEffectue = isPleinEffectue;
        this.nbKmsEffectues = nbKmsEffectues;
        this.photo = photo;
    }

    public Retour(Location location, boolean isEndommage, boolean isPleinEffectue, int nbKmsEffectues)
    {
        this.location = location;
        this.isEndommage = isEndommage;
        this.isPleinEffectue = isPleinEffectue;
        this.nbKmsEffectues = nbKmsEffectues;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public boolean isEndommage()
    {
        return isEndommage;
    }

    public void setEndommage(boolean endommage)
    {
        isEndommage = endommage;
    }

    public boolean isPleinEffectue()
    {
        return isPleinEffectue;
    }

    public void setPleinEffectue(boolean pleinEffectue)
    {
        isPleinEffectue = pleinEffectue;
    }

    public int getNbKmsEffectues()
    {
        return nbKmsEffectues;
    }

    public void setNbKmsEffectues(int nbKmsEffectues)
    {
        this.nbKmsEffectues = nbKmsEffectues;
    }

    public String getPhoto()
    {
        return photo;
    }

    public void setPhoto(String photo)
    {
        this.photo = photo;
    }
}