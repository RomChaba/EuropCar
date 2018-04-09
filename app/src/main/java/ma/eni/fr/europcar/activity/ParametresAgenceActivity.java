package ma.eni.fr.europcar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.enums.TypeErreur;
import ma.eni.fr.europcar.fragment.ConnexionFragment;
import ma.eni.fr.europcar.fragment.ParametresAgenceFragment;
import ma.eni.fr.europcar.service.AgenceService;
import ma.eni.fr.europcar.service.UtilisateurService;

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
    public void parametresAgenceValide(String raisonSociale, String siret, String voie, String codePostal, String ville)
    {
        TypeErreur resultat = this.agenceService.ajouter(raisonSociale, siret, voie, codePostal, ville);

        if(!TypeErreur.OK.equals(resultat))
        {
            Toast.makeText(this, resultat.getMessage(), Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Informations enregistrées", Toast.LENGTH_LONG).show();
        }
    }
}