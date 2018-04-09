package ma.eni.fr.europcar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import ma.eni.fr.europcar.enums.TypeErreur;
import ma.eni.fr.europcar.fragment.ConnexionFragment;
import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.enums.TypeAffichage;
import ma.eni.fr.europcar.service.UtilisateurService;

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

        this.utilisateurService = new UtilisateurService();
        fragment = (ConnexionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_connexion);
        fragment.setTypeAffichage(TypeAffichage.INSCRIPTION);
    }

    @Override
    public void redirectionVersInscription()
    {

    }

    @Override
    public void connexionValider(String email, String motDePasse)
    {

    }

    @Override
    public void inscriptionValider(String email, String motDePasse)
    {
        TypeErreur resultat = this.utilisateurService.inscription(email, motDePasse);

        if(!TypeErreur.OK.equals(resultat))
        {
            Toast.makeText(this, resultat.getMessage(), Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(InscriptionActivity.this, ParametresAgenceActivity.class);
            startActivity(intent);
        }
    }
}