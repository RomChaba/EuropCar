package ma.eni.fr.europcar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.fragment.LocationFragment;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.service.LocationService;

public class LocationActivity extends AppCompatActivity implements LocationFragment.OnFragmentInteractionListener {

    private LocationFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
    }

    @Override
    protected void onResume() {
        super.onResume();

        fragment = (LocationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_location);
        fragment.refreshList(LocationService.getInstance().getLocationList());
    }
}
