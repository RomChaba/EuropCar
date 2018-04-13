package ma.eni.fr.europcar.dao;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import ma.eni.fr.europcar.model.Agence;
import ma.eni.fr.europcar.utils.OF;

/**
 * Created by Administrateur on 13/04/2018.
 */

public class AgenceHTTP implements IAgenceDAO
{
    private Context context;
    private final String METHODE = "agences";

    public AgenceHTTP(Context context)
    {
        this.context = context;
    }

    @Override
    public Agence getAgenceAvecId(String id)
    {
        Agence agence = null;

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
            response = future.get(10, TimeUnit.SECONDS);
            try
            {
                agence = getAgenceAvecJSON((JSONObject) response.get("agence"));
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

        return agence;
    }

    @Override
    public HashMap<String, String> updateAgence(Agence agence, String idUtilisateur)
    {
        HashMap<String, String> resultat = new HashMap<String, String>();

        // Url de la requête
        String url = OF.getIp(context) + METHODE + "/" + agence.getId() + "/" + idUtilisateur;

        // Paramètres
        HashMap<String, String> parametres = new HashMap<String, String>();
        parametres.put("agenceID", agence.getId());
        parametres.put("raisonSociale", agence.getRaisonSociale());
        parametres.put("siret", agence.getSiret());
        parametres.put("voie", agence.getVoie());
        parametres.put("codePostal", agence.getCodePostal());
        parametres.put("ville", agence.getVille());

        // Création de la requette
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, new JSONObject(parametres), future, future);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);

        JSONObject response = null;
        try
        {
            // Tant que la réponse n'est pas celle attendue
            response = future.get(10, TimeUnit.SECONDS);
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

    private Agence getAgenceAvecJSON(JSONObject object)
    {
        Agence agence = null;

        if(object != null)
        {
            try
            {
                agence = new Agence();
                agence.setId(object.getString("agenceID"));
                agence.setRaisonSociale(object.getString("raisonSociale"));
                agence.setSiret(object.getString("siret"));
                agence.setVoie(object.getString("voie"));
                agence.setCodePostal(object.getString("codePostal"));
                agence.setVille(object.getString("ville"));
            }
            catch (JSONException e)
            {
                return null;
            }
        }

        return agence;
    }
}
