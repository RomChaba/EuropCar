package ma.eni.fr.europcar.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

        fragment = (LocationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_location);

        LocationService locationService = new LocationService();
        fragment.refreshList(locationService.getLocationListEnCours());

        btn_ajout_Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationActivity.this,ListeVehiculeActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void afficherDetailLocation(Location location)
    {
        Intent intent = new Intent(LocationActivity.this, RetourActivity.class);
        intent.putExtra("idLocation", location.getId());
        startActivity(intent);
    }
}