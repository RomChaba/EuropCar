package ma.eni.fr.europcar.activity;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.fragment.ReservationFragment;
import ma.eni.fr.europcar.fragment.VehiculeFragment;
import ma.eni.fr.europcar.model.Agence;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Utilisateur;
import ma.eni.fr.europcar.model.Vehicule;
import ma.eni.fr.europcar.service.AgenceService;
import ma.eni.fr.europcar.service.LocationService;
import ma.eni.fr.europcar.service.UtilisateurService;
import ma.eni.fr.europcar.service.VehiculeService;

public class ReservationActivity extends AppCompatActivity implements ReservationFragment.OnFragmentInteractionListener,VehiculeFragment.OnFragmentInteractionListener {

    VehiculeFragment vehiculeFragment;
    ReservationFragment reservationFragment;
    List<Vehicule> vehiculeList = new ArrayList<>();
    Vehicule vehicule;
    VehiculeService vehiculeService;
    AgenceService agenceService;
    LocationService locationService;
    Agence agence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        // Récupération de l'utilisateur en cours
        this.vehiculeService = new VehiculeService(this);
        this.agenceService = new AgenceService(this);
        this.locationService = new LocationService(this);
        }

    private class ReservationVehiculeAsync extends AsyncTask<String,Void,Void>{

        Context context;

        public ReservationVehiculeAsync(Context context)
        {
            this.context = context;
        }

        @Override
        protected Void doInBackground(String... strings)
        {
            vehicule = vehiculeService.getVehiculeById(strings[0]);
            vehiculeList.add(vehicule);
            vehiculeFragment = (VehiculeFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_liste_vehicule);
            reservationFragment = (ReservationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_form_reservation);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);

            vehiculeFragment.refreshList(vehiculeList);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        String id = getIntent().getStringExtra("idVehicule");
        ReservationVehiculeAsync task = new ReservationVehiculeAsync(this);
        task.execute(id);
        AgenceAsynkTask task1 = new AgenceAsynkTask(this);
        task1.execute();
    }

    @Override
    public void reservationValider(String date_debut, String date_fin, String tarif_jouralier) {

        SimpleDateFormat dt_debut = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dt_fin = new SimpleDateFormat("dd/MM/yyyy");
        Date temp1 = null;
        Date temp2 = null;

        try {
            temp1 = dt_debut.parse(date_debut);
            temp2 = dt_fin.parse(date_fin);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        Location loca = new Location("",temp1,temp2,Float.valueOf(tarif_jouralier),vehicule,true, agence);
        ReservationAsync task = new ReservationAsync(ReservationActivity.this);
        task.execute(loca);


        Intent intent = new Intent(ReservationActivity.this,LocationActivity.class);
        startActivity(intent);
    }

    @Override
    public void onVehiculeClick(Vehicule vehicule) {

    }

    private class AgenceAsynkTask extends AsyncTask<String, Void, Void>
    {
        private Context context;

        public AgenceAsynkTask(Context context)
        {
            this.context = context;
        }

        @Override
        protected Void doInBackground(String... strings)
        {
            SharedPreferences sharedPreferences = context.getSharedPreferences("utilisateur", Context.MODE_PRIVATE);
            String idAgence = sharedPreferences.getString("idAgence", "");
            agence = agenceService.getAgenceAvecId(idAgence);

            return null;
        }
    }

    private class ReservationAsync extends AsyncTask<Location,Void,Void>
    {
        Context context;
        HashMap<String, String> resultat;

        public ReservationAsync(Context context)
        {
            this.context = context;
            resultat = new HashMap<String, String>();
        }

        @Override
        protected Void doInBackground(Location... locations)
        {
            SharedPreferences sharedPreferences = context.getSharedPreferences("utilisateur", Context.MODE_PRIVATE);
            String idUtilisateur = sharedPreferences.getString("idUtilisateur", "");
            resultat = locationService.reservation(locations[0], idUtilisateur);

            return null;
        }
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            if(this.resultat.containsKey("error"))
            {
                Toast.makeText(context, this.resultat.get("error"), Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(context, getResources().getString(R.string.enristrement_reservation), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
