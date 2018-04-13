package ma.eni.fr.europcar.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.dao.UtilisateurBouchon;
import ma.eni.fr.europcar.enums.Message;
import ma.eni.fr.europcar.enums.TypeErreur;
import ma.eni.fr.europcar.fragment.ConnexionFragment;
import ma.eni.fr.europcar.fragment.ParametresAgenceFragment;
import ma.eni.fr.europcar.model.Agence;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Utilisateur;
import ma.eni.fr.europcar.service.AgenceService;
import ma.eni.fr.europcar.service.UtilisateurService;
import ma.eni.fr.europcar.utils.OF;

public class ParametresAgenceActivity extends AppCompatActivity implements ParametresAgenceFragment.ParametresListener
{
    private ParametresAgenceFragment fragment;
    private AgenceService agenceService;
    private Agence agence;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres_agence);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        fragment = (ParametresAgenceFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_parametres_agence);
        this.agenceService = new AgenceService(this);
        AgenceAsynkTask task = new AgenceAsynkTask(this);
        task.execute();
    }

    @Override
    public void parametresAgenceValide(Agence agence)
    {
        ParametresAgenceAsyncTask task = new ParametresAgenceAsyncTask(this);
        task.execute(agence);
    }

    private class AgenceAsynkTask extends AsyncTask<String, Void, Void>
    {
        private Context context;

        public AgenceAsynkTask(Context context)
        {
            this.context = context;
        }

        @Override
        protected Void doInBackground(String... strings)
        {
            SharedPreferences sharedPreferences = context.getSharedPreferences("utilisateur", Context.MODE_PRIVATE);
            String idAgence = sharedPreferences.getString("idAgence", "");
            agence = agenceService.getAgenceAvecId(idAgence);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);

            fragment.refreshAgence(agence);
        }
    }

    private class ParametresAgenceAsyncTask extends AsyncTask<Agence, Void, Void>
    {
        private Context context;
        private HashMap<String, String> resultat;
        private Agence agence;


        public ParametresAgenceAsyncTask(Context context)
        {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Agence... agences)
        {
            this.agence = agences[0];

            SharedPreferences sharedPreferences = context.getSharedPreferences("utilisateur", Context.MODE_PRIVATE);
            String idUtilisateur = sharedPreferences.getString("idUtilisateur", "");
            resultat = agenceService.updateAgence(this.agence, idUtilisateur);

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values)
        {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);

            if(this.resultat.containsKey("error") && !this.resultat.get("error").isEmpty())
            {
                Toast.makeText(context, this.resultat.get("error"), Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(context, OF.getStringByName(context, Message.PARAMETRES_AGENCE), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, LocationActivity.class);
                startActivity(intent);
            }
        }
    }
}