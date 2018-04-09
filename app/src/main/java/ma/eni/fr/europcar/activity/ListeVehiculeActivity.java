package ma.eni.fr.europcar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.fragment.VehiculeFragment;
import ma.eni.fr.europcar.service.VehiculeService;

public class ListeVehiculeActivity extends AppCompatActivity implements VehiculeFragment.OnFragmentInteractionListener {
    VehiculeFragment vehiculeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_vehicule);

        vehiculeFragment = (VehiculeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_liste_vehicule);



        vehiculeFragment.refreshList(VehiculeService.getInstance().getListeDesVehiculesDisponibles());

    }
}
