package ma.eni.fr.europcar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.net.Inet4Address;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.fragment.VehiculeFragment;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Vehicule;
import ma.eni.fr.europcar.service.LocationService;
import ma.eni.fr.europcar.service.VehiculeService;

public class ListeVehiculeActivity extends AppCompatActivity implements VehiculeFragment.OnFragmentInteractionListener {
    VehiculeFragment vehiculeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_vehicule);

        vehiculeFragment = (VehiculeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_liste_vehicule);

        VehiculeService vehiculeService = new VehiculeService();

        vehiculeFragment.refreshList(vehiculeService.getListeDesVehiculesDisponibles());

    }

    @Override
    public void onVehiculeClick(Vehicule vehicule) {
        Intent intent = new Intent(ListeVehiculeActivity.this,ReservationActivity.class);

        intent.putExtra("idVehicule",vehicule.getId());

        startActivity(intent);


    }
}
