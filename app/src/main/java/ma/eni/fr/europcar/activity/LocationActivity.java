package ma.eni.fr.europcar.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.fragment.LocationFragment;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.service.LocationService;

public class LocationActivity extends AppCompatActivity implements LocationFragment.OnFragmentInteractionListener {

    private LocationFragment fragment;
    private FloatingActionButton btn_ajout_Location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btn_ajout_Location = findViewById(R.id.btn_nouvelle_location);

        fragment = (LocationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_location);
        fragment.refreshList(LocationService.getInstance().getLocationList());

        btn_ajout_Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationActivity.this,ListeVehiculeActivity.class);
                startActivity(intent);
            }
        });


    }
}
