package ma.eni.fr.europcar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.enums.Message;
import ma.eni.fr.europcar.enums.TypeErreur;
import ma.eni.fr.europcar.fragment.ConnexionFragment;
import ma.eni.fr.europcar.fragment.ParametresAgenceFragment;
import ma.eni.fr.europcar.model.Agence;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.service.AgenceService;
import ma.eni.fr.europcar.service.UtilisateurService;
import ma.eni.fr.europcar.utils.OF;

public class ParametresAgenceActivity extends AppCompatActivity implements ParametresAgenceFragment.ParametresListener
{
    ParametresAgenceFragment fragment;
    AgenceService agenceService;

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

        this.agenceService = new AgenceService();
        fragment = (ParametresAgenceFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_parametres_agence);
    }

    @Override
    public void parametresAgenceValide(Agence agence)
    {
        TypeErreur resultat = this.agenceService.getInstance().ajouter(agence);

        if(!TypeErreur.OK.equals(resultat))
        {
            Toast.makeText(this, OF.getStringByName(this, resultat), Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, OF.getStringByName(this, Message.PARAMETRES_AGENCE), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ParametresAgenceActivity.this, LocationActivity.class);
            startActivity(intent);
        }
    }
}