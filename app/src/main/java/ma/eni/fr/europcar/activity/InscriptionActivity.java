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

public class InscriptionActivity extends AppCompatActivity implements ConnexionFragment.ConnexionListener
{
    ConnexionFragment fragment;
    UtilisateurService utilisateurService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        this.utilisateurService = new UtilisateurService(this);
        fragment = (ConnexionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_connexion);
        fragment.setTypeAffichage(TypeAffichage.INSCRIPTION);
    }

    @Override
    public void redirectionVersInscription()
    {

    }

    @Override
    public void connexionValider(Utilisateur utilisateur)
    {

    }

    @Override
    public void inscriptionValider(Utilisateur utilisateur)
    {
        InscriptionAsyncTask task = new InscriptionAsyncTask(this);
        task.execute(utilisateur);
    }

    private class InscriptionAsyncTask extends AsyncTask<Utilisateur, Void, Void>
    {
        private Context context;
        private TypeErreur resultat;

        public InscriptionAsyncTask(Context context)
        {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Utilisateur... utilisateurs)
        {
            this.resultat = utilisateurService.inscription(utilisateurs[0]);

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values)
        {
            super.onProgressUpdate(values);

            Toast.makeText(context, OF.getStringByName(context, Message.INSCRIPTION), Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);

            if(!TypeErreur.OK.equals(resultat))
            {
                Toast.makeText(context, OF.getStringByName(context, resultat), Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, OF.getStringByName(context, Message.INSCRIPTION_REUSSIE), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ParametresAgenceActivity.class);
                startActivity(intent);
            }
        }
    }
}