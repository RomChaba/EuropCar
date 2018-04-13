package ma.eni.fr.europcar.dao;

import android.content.Context;
import android.util.JsonWriter;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import ma.eni.fr.europcar.model.Agence;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Retour;
import ma.eni.fr.europcar.model.Vehicule;
import ma.eni.fr.europcar.service.AgenceService;
import ma.eni.fr.europcar.service.VehiculeService;
import ma.eni.fr.europcar.utils.OF;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class LocationHTTP implements ILocationDAO
{
    Context context;
    private final String METHODE = "reservations";


    public LocationHTTP(Context context) {
        this.context = context;
    }


    @Override
    public HashMap<String, String> reservation(Location location, String idUtilisateur)
    {
        HashMap<String, String> resultat = new HashMap<String, String>();

        // Url de la requête
        String url = OF.getIp(context) + METHODE + "/" + idUtilisateur;

        //Création des données a envoyer au serveur

        // Paramètres
        HashMap<String, String> parametres = new HashMap<String, String>();
        parametres.put("vehiculeID",location.getVehicule().getId());
        parametres.put("agenceID",location.getAgence().getId());
        parametres.put("dateDebut", String.valueOf(location.getDate_debut().getTime()));
        parametres.put("dateFin", String.valueOf(location.getDate_fin().getTime()));
        parametres.put("tarifJournalier", String.valueOf(location.getTarif_journalier()));

        // Création de la requette
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(parametres), future, future);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

        JSONObject response = null;
        try
        {
            // Tant que la réponse n'est pas celle attendue
            response = future.get(10, TimeUnit.SECONDS);
            try
            {
                JSONArray liste = (JSONArray) response.get("locations");
            }
            catch (JSONException e)
            {
                Log.i(context.getClass().getName(), e.getMessage());
            }
        }
        catch (InterruptedException|TimeoutException e)
        {
            Log.i(context.getClass().getName(), e.getMessage());
        }
        catch (ExecutionException e)
        {
            resultat.put("error", OF.getApiError(e));
        }

        return resultat;
    }

    @Override
    public void updateLocation(Location location) {

    }

    @Override
    public boolean rendre(Retour rendu)
    {
        return false;
    }


    @Override
    public List<Location> getListLocation(String idAgence) {
        final List<Location> locations = new ArrayList<Location>();

        // Url de la requête
        String url = OF.getIp(context) + METHODE + "/" + idAgence;

        // Création de la requette
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, future, future);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

        JSONObject response = null;
        try
        {
            // Tant que la réponse n'est pas celle attendue
            response = future.get(10, TimeUnit.SECONDS);
            try
            {
                JSONArray liste = (JSONArray) response.get("locations");
                if(liste != null)
                {
                    for (int i = 0; i < liste.length(); i++)
                    {
                        Location location = getLocationAvecJSON((JSONObject) liste.get(i));
                        if(location != null)
                        {
                            locations.add(location);
                        }
                    }
                }
            }
            catch (JSONException e)
            {
                Log.i(context.getClass().getName(), e.getMessage());
            }
        }
        catch (InterruptedException|ExecutionException |TimeoutException e)
        {
            Log.i(context.getClass().getName(), e.getMessage());
        }

        return locations;
    }

    @Override
    public List<Location> getListLocationEnCours(String idAgence) {
        return getListLocation(idAgence);
    }

    @Override
    public Location getLocationById(String id) {
        Location location = null;

        // Url de la requête
        String url = OF.getIp(context) + METHODE + "/" + id;

        // Création de la requette
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, future, future);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

        JSONObject response = null;
        try
        {
            // Tant que la réponse n'est pas celle attendue
            response = future.get(10, TimeUnit.DAYS);
            try
            {
                location = getLocationAvecJSON((JSONObject) response.get("vehicule"));
            }
            catch (JSONException e)
            {
                Log.i(context.getClass().getName(), e.getMessage());
            }
        }
        catch (InterruptedException|ExecutionException|TimeoutException e)
        {
            Log.i(context.getClass().getName(), e.getMessage());
        }

        return location;
    }

    /**
     * Transforme un JSONObject en une instance de Location
     * @param object JSONObject
     * @return Location
     */
    private Location getLocationAvecJSON(JSONObject object)
    {
        Location location = null;

        if(object != null)
        {
            try
            {
                VehiculeService vehiculeService = new VehiculeService(context);
                AgenceService agenceService = new AgenceService(context);


//                Location(int id, Date date_debut, Date date_fin, float tarif_journalier, Vehicule vehicule, boolean isEnCours, Agence agence)
                location = new Location();
                location.setId(object.getString("reservationID"));
                location.setDate_debut(new Date(Long.valueOf(object.getString("dateDebut"))));
                location.setDate_fin(new Date(Long.valueOf(object.getString("dateFin"))));
                location.setTarif_journalier(Float.valueOf(object.getString("tarifJournalier")));
                location.setVehicule(vehiculeService.getVehiculeById(object.getString("vehiculeID")));
                location.setEnCours(Boolean.valueOf(object.getString("isEnCours")));
                //TODO REMPLACER PAR L'AGENCE DE LA RQT HTTP
//                Agence(String raisonSociale, String siret, String voie, int codePostal, String ville)
                location.setAgence(agenceService.getAgenceAvecId(object.getString("agenceID")));
            }
            catch (JSONException e)
            {
                return null;
            }
        }

        return location;
    }



}
