package ma.eni.fr.europcar.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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
    ParametresAgenceFragment fragment;
    AgenceService agenceService;
    UtilisateurService utilisateurService;
    Utilisateur utilisateur;

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

        this.agenceService = new AgenceService(this);
        this.utilisateurService = new UtilisateurService(this);
        int idUtilisateur = getIntent().getIntExtra("idUtilisateur", -1);
        utilisateur = utilisateurService.getUtilisateurAvecId(idUtilisateur);
        fragment = (ParametresAgenceFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_parametres_agence);
    }

    @Override
    public void parametresAgenceValide(Agence agence)
    {
        ParametresAgenceAsyncTask task = new ParametresAgenceAsyncTask(this);
        task.execute(agence);
    }

    private class ParametresAgenceAsyncTask extends AsyncTask<Agence, Void, Void>
    {
        private Context context;
        private TypeErreur resultat;

        public ParametresAgenceAsyncTask(Context context)
        {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Agence... agences)
        {
            this.resultat = agenceService.ajouter(agences[0]);

            if(TypeErreur.OK.equals(resultat))
            {
                if(utilisateur != null)
                {
                    utilisateur.setAgence(agences[0]);
                    utilisateurService.updateUtilisateur(utilisateur);
                }
            }

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

            if(!TypeErreur.OK.equals(resultat))
            {
                Toast.makeText(context, OF.getStringByName(context, resultat), Toast.LENGTH_LONG).show();
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