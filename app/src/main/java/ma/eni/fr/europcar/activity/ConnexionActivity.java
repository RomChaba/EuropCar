package ma.eni.fr.europcar.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import ma.eni.fr.europcar.enums.Message;
import ma.eni.fr.europcar.enums.TypeErreur;
import ma.eni.fr.europcar.fragment.ConnexionFragment;
import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.enums.TypeAffichage;
import ma.eni.fr.europcar.model.Utilisateur;
import ma.eni.fr.europcar.service.UtilisateurService;
import ma.eni.fr.europcar.utils.OF;

public class ConnexionActivity extends AppCompatActivity implements ConnexionFragment.ConnexionListener
{
    ConnexionFragment connexionFragment;
    UtilisateurService utilisateurService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_connexion);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        this.utilisateurService = new UtilisateurService(this);
        this.connexionFragment = ((ConnexionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_connexion));
        this.connexionFragment.setTypeAffichage(TypeAffichage.CONNEXION);
    }

    @Override
    public void redirectionVersInscription()
    {
        Intent intent = new Intent(ConnexionActivity.this, InscriptionActivity.class);
        startActivity(intent);
    }

    @Override
    public void connexionValider(Utilisateur utilisateur)
    {
        ConnexionAsyncTask task = new ConnexionAsyncTask(this);
        task.execute(utilisateur);
    }

    @Override
    public void inscriptionValider(Utilisateur utilisateur)
    {

    }

    private class ConnexionAsyncTask extends AsyncTask<Utilisateur, Void, Void>
    {
        private Context context;
        private TypeErreur resultat;

        public ConnexionAsyncTask(Context context)
        {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Utilisateur... utilisateurs)
        {
            this.resultat = utilisateurService.connexion(utilisateurs[0]);

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values)
        {
            super.onProgressUpdate(values);

            Toast.makeText(context, OF.getStringByName(context, Message.CONNEXION), Toast.LENGTH_LONG).show();
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
                Toast.makeText(context, OF.getStringByName(context, Message.CONNEXION_REUSSIE), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, LocationActivity.class);
                startActivity(intent);
            }
        }
    }
}