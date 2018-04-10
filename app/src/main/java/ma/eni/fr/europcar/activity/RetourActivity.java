package ma.eni.fr.europcar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.enums.TypeErreur;
import ma.eni.fr.europcar.fragment.RetourFragment;
import ma.eni.fr.europcar.fragment.LocationFragment;
import ma.eni.fr.europcar.model.Location;
import ma.eni.fr.europcar.service.LocationService;
import ma.eni.fr.europcar.service.RetourService;
import ma.eni.fr.europcar.utils.OF;

public class RetourActivity extends AppCompatActivity implements LocationFragment.LocationListener, RetourFragment.RendreLocationListener
{
    private LocationFragment locationFragment;
    private RetourFragment retourFragment;
    private Location location;
    private RetourService retourService;

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

        this.retourService = new RetourService();
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
    public void rendreLocation(boolean estEndommagee, boolean pleinFait, String kms, String photo)
    {
        TypeErreur resultat = this.retourService.rendre(this.location, estEndommagee, pleinFait, kms, photo);

        if(!TypeErreur.OK.equals(resultat))
        {
            Toast.makeText(this, OF.getStringByName(this, resultat.name()), Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Location rendue", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RetourActivity.this, LocationActivity.class);
            startActivity(intent);
        }
    }
}