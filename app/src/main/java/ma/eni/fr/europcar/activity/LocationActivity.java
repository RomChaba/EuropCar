package ma.eni.fr.europcar.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.fragment.LocationFragment;
import ma.eni.fr.europcar.model.Agence;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Utilisateur;
import ma.eni.fr.europcar.service.LocationService;
import ma.eni.fr.europcar.service.UtilisateurService;

public class LocationActivity extends AppCompatActivity implements LocationFragment.LocationListener
{
    private LocationFragment fragment;
    private FloatingActionButton btn_ajout_Location;
    private TextView aucuneLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        btn_ajout_Location = findViewById(R.id.btn_nouvelle_location);
        aucuneLocation = findViewById(R.id.aucune_location);

        fragment = (LocationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_location);

        LocationService locationService = new LocationService();
        List<Location> locationsEnCours = locationService.getLocationListEnCours();
        fragment.refreshList(locationsEnCours);

        btn_ajout_Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationActivity.this,ListeVehiculeActivity.class);
                startActivity(intent);
            }
        });

        if(locationsEnCours != null && locationsEnCours.size() == 0)
        {
            aucuneLocation.setVisibility(View.VISIBLE);
        }
        else
        {
            aucuneLocation.setVisibility(View.GONE);
        }
    }

    @Override
    public void afficherDetailLocation(Location location)
    {
        Intent intent = new Intent(LocationActivity.this, RetourActivity.class);
        intent.putExtra("idLocation", location.getId());
        startActivity(intent);
    }
}