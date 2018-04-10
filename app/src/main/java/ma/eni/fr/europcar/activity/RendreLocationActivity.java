package ma.eni.fr.europcar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.fragment.RendreLocationFragment;
import ma.eni.fr.europcar.fragment.LocationFragment;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.service.LocationService;

public class RendreLocationActivity extends AppCompatActivity implements LocationFragment.LocationListener, RendreLocationFragment.RendreLocationListener
{
    private LocationFragment fragment;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendre_location);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        this.fragment = (LocationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_location);
        int idLocation = getIntent().getIntExtra("idLocation", - 1);
        this.location = LocationService.getInstance().getLocationAvecId(idLocation);

        if(location != null)
        {
            List<Location> locations = new ArrayList<>();
            locations.add(location);
            fragment.refreshList(locations);
        }
        else
        {
            Toast.makeText(this, "Une erreur est survenue", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RendreLocationActivity.this, LocationActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void afficherDetailLocation(Location location)
    {

    }
}