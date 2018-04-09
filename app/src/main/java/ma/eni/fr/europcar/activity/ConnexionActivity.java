package ma.eni.fr.europcar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ma.eni.fr.europcar.ConnexionFragment;
import ma.eni.fr.europcar.R;

public class ConnexionActivity extends AppCompatActivity implements ConnexionFragment.ConnexionListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
    }
}