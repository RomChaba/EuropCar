package ma.eni.fr.europcar.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.enums.Message;
import ma.eni.fr.europcar.enums.TypeErreur;
import ma.eni.fr.europcar.fragment.RetourFragment;
import ma.eni.fr.europcar.fragment.LocationFragment;
import ma.eni.fr.europcar.model.Agence;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.model.Retour;
import ma.eni.fr.europcar.service.LocationService;
import ma.eni.fr.europcar.service.RetourService;
import ma.eni.fr.europcar.utils.OF;

public class RetourActivity extends AppCompatActivity implements LocationFragment.LocationListener, RetourFragment.RendreLocationListener
{
    private LocationFragment locationFragment;
    private RetourFragment retourFragment;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retour);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        this.locationFragment = (LocationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_location);
        this.retourFragment = (RetourFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_retour);
        int idLocation = getIntent().getIntExtra("idLocation", - 1);
        this.location = LocationService.getInstance().getLocationAvecId(idLocation);

        if(location != null)
        {
            List<Location> locations = new ArrayList<>();
            locations.add(location);
            locationFragment.refreshList(locations);
        }
        else
        {
            Intent intent = new Intent(RetourActivity.this, LocationActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void afficherDetailLocation(Location location)
    {

    }

    @Override
    public void rendreLocation(Retour retour)
    {
        retour.setLocation(this.location);
        RetourAsyncTask task = new RetourAsyncTask(this);
        task.execute(retour);
    }

    private class RetourAsyncTask extends AsyncTask<Retour, Void, Void>
    {
        private Context context;
        private TypeErreur resultat;

        public RetourAsyncTask(Context context)
        {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Retour... retours)
        {
            this.resultat = RetourService.getInstance().rendre(retours[0]);

            if(TypeErreur.OK.equals(resultat))
            {
                location.setEnCours(false);
                LocationService.getInstance().updateLocation(location);
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values)
        {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);

            if(!TypeErreur.OK.equals(resultat))
            {
                Toast.makeText(context, OF.getStringByName(context, resultat), Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(context, OF.getStringByName(context, Message.LOCATION_RENDUE), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RetourActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        }
    }
}