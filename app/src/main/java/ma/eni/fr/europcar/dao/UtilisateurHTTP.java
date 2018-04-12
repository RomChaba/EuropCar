package ma.eni.fr.europcar.dao;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import ma.eni.fr.europcar.model.Utilisateur;
import ma.eni.fr.europcar.utils.OF;

/**
 * Created by Administrateur on 12/04/2018.
 */

public class UtilisateurHTTP implements IUtilisateurDAO
{
    private Context context;
    private final String INSCRIPTION = "subscribe";
    private final String CONNEXION = "connect";

    public UtilisateurHTTP(Context context)
    {
        this.context = context;
    }

    @Override
    public Utilisateur getUtilisateurAvecId(int id)
    {
        return null;
    }

    @Override
    public Utilisateur getUtilisateurAvecEmail(String email)
    {
        return null;
    }

    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur)
    {

    }

    @Override
    public List<Utilisateur> getListeDesUtilisateurs()
    {
        return null;
    }

    @Override
    public void updateUtilisateur(Utilisateur utilisateur)
    {

    }

    @Override
    public HashMap<String, String> connexion(Utilisateur utilisateur)
    {
        HashMap<String, String> resultat = new HashMap<String, String>();

        // Url de la requête
        String url = OF.getIp(context) + CONNEXION;

        HashMap<String, String> parametres = new HashMap<String, String>();
        parametres.put("mail", utilisateur.getEmail());
        parametres.put("password", utilisateur.getMotDePasse());

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
                String agenceID = response.getString("agenceID");
                if(agenceID != null)
                {
                    resultat.put("agenceID", agenceID);
                }
                String userID = response.getString("userID");
                if(userID != null)
                {
                    resultat.put("userID", userID);
                }
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
}