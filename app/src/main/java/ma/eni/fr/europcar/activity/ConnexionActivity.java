package ma.eni.fr.europcar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ma.eni.fr.europcar.ConnexionFragment;
import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.enums.TypeAffichage;

import static ma.eni.fr.europcar.R.id.fragment_connexion;

public class ConnexionActivity extends AppCompatActivity implements ConnexionFragment.ConnexionListener
{
    ConnexionFragment connexionFragment;

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

        this.connexionFragment = ((ConnexionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_connexion));
        this.connexionFragment.setTypeAffichage(TypeAffichage.CONNEXION);
    }

    @Override
    public void redirectionVersInscription(){
        Intent intent = new Intent(ConnexionActivity.this,InscriptionActivity.class);
        startActivity(intent);
    }

    @Override
    public void connexionValider(){

    }
    @Override
    public void inscriptionValider(){

    }
}