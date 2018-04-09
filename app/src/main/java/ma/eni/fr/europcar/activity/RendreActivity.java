package ma.eni.fr.europcar.activity;

import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.fragment.LocationFragment;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.service.LocationService;

public class RendreActivity extends AppCompatActivity implements LocationFragment.OnFragmentInteractionListener
{
    private LocationFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendre);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        fragment = (LocationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_location);

        Location location = LocationService.getInstance().getLocationAvecId(1);
        if(location != null)
        {
            List<Location> locations = new ArrayList<>();
            locations.add(location);
            fragment.refreshList(locations);
        }
    }
}