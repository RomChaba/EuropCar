package ma.eni.fr.europcar.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.net.Inet4Address;
import java.util.List;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.fragment.VehiculeFragment;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Utilisateur;
import ma.eni.fr.europcar.model.Vehicule;
import ma.eni.fr.europcar.service.LocationService;
import ma.eni.fr.europcar.service.VehiculeService;

public class ListeVehiculeActivity extends AppCompatActivity implements VehiculeFragment.OnFragmentInteractionListener
{
    VehiculeFragment vehiculeFragment;
    VehiculeService vehiculeService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_vehicule);
        vehiculeFragment = (VehiculeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_liste_vehicule);
        vehiculeService = new VehiculeService(this);
        ListeVehiculeAsyncTask task = new ListeVehiculeAsyncTask();
        task.execute();
    }

    @Override
    public void onVehiculeClick(Vehicule vehicule)
    {
        Intent intent = new Intent(ListeVehiculeActivity.this,ReservationActivity.class);
        intent.putExtra("idVehicule",vehicule.getId());
        startActivity(intent);
    }

    private class ListeVehiculeAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private Context context;
        private List<Vehicule> vehicules;

        @Override
        protected Void doInBackground(Void... voids)
        {
            vehicules = vehiculeService.getListeDesVehiculesDisponibles();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);

            vehiculeFragment.refreshList(vehicules);
        }
    }
}
