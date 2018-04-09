package ma.eni.fr.europcar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import ma.eni.fr.europcar.enums.TypeErreur;
import ma.eni.fr.europcar.fragment.ConnexionFragment;
import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.enums.TypeAffichage;
import ma.eni.fr.europcar.model.Utilisateur;
import ma.eni.fr.europcar.service.UtilisateurService;

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

        this.utilisateurService = new UtilisateurService();
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
    public void connexionValider(String email, String motDePasse)
    {
        TypeErreur resultat = this.utilisateurService.getInstance().connexion(email, motDePasse);

        if(!TypeErreur.OK.equals(resultat))
        {
            Toast.makeText(this, resultat.getMessage(), Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Connexion...", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ConnexionActivity.this, LocationActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void inscriptionValider(String email, String motDePasse)
    {

    }
}