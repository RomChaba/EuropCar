package ma.eni.fr.europcar.dao;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import ma.eni.fr.europcar.model.Vehicule;
import ma.eni.fr.europcar.utils.OF;

/**
 * Created by Administrateur on 12/04/2018.
 */

public class VehiculeHTTP implements IVehiculeDAO
{
    private Context context;
    private final String METHODE = "vehicules";

    public VehiculeHTTP(Context context)
    {
        this.context = context;
    }

    @Override
    public List<Vehicule> getListVehicule()
    {
        final List<Vehicule> vehicules = new ArrayList<Vehicule>();

        // Url de la requête
        String url = OF.getIp(context) + METHODE;

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
                JSONArray liste = (JSONArray) response.get("vehicules");
                if(liste != null)
                {
                    for (int i = 0; i < liste.length(); i++)
                    {
                        Vehicule vehicule = getVehiculeAvecJSON((JSONObject) liste.get(i));
                        if(vehicule != null)
                        {
                            vehicules.add(vehicule);
                        }
                    }
                }
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

        return vehicules;
    }

    @Override
    public Vehicule getVehiculeById(String id)
    {
        Vehicule vehicule = null;

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
                vehicule = getVehiculeAvecJSON((JSONObject) response.get("vehicule"));
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

        return vehicule;
    }

    /**
     * Transforme un JSONObject en une instance de Vehicule
     * @param object JSONObject
     * @return Vehicule
     */
    private Vehicule getVehiculeAvecJSON(JSONObject object)
    {
        Vehicule vehicule = null;

        if(object != null)
        {
            try
            {
                vehicule = new Vehicule();
                vehicule.setId(object.getString("vehiculeID"));
                vehicule.setNbPlaces(object.getInt("nbPlaces"));
                vehicule.setLocationMin(object.getInt("locationMin"));
                vehicule.setLocationMax(object.getInt("locationMax"));
                vehicule.setTarifMin(object.getLong("tarifMin"));
                vehicule.setTarifMax(object.getLong("tarifMax"));
                vehicule.setTarifMoyen(object.getLong("tarifMoyen"));
                vehicule.setDisponible(object.getBoolean("isDisponible"));
            }
            catch (JSONException e)
            {
                return null;
            }
        }

        return vehicule;
    }
}
