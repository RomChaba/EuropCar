package ma.eni.fr.europcar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ma.eni.fr.europcar.fragment.ConnexionFragment;
import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.enums.TypeAffichage;

public class InscriptionActivity extends AppCompatActivity implements ConnexionFragment.ConnexionListener{
    ConnexionFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
    }

    @Override
    protected void onResume() {
        super.onResume();

        fragment = (ConnexionFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_connexion);

        fragment.setTypeAffichage(TypeAffichage.INSCRIPTION);

    }

    @Override
    public void redirectionVersInscription() {

    }

    @Override
    public void connexionValider() {

    }

    @Override
    public void inscriptionValider() {

    }
}
