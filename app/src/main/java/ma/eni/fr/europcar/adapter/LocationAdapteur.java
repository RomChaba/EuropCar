package ma.eni.fr.europcar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.model.Location;

/**
 * Created by Romain on 09/04/2018.
 */

public class LocationAdapteur extends ArrayAdapter<Location> {

    private Context context;
    private int layout;
    private List<Location> locationList;

    private TextView idVehicule;
    private TextView date_debut;
    private TextView date_fin;
    private TextView tarif_journalier;




    public LocationAdapteur(@NonNull Context context, int resource, @NonNull List<Location> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.locationList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.layout,parent,false);
        }

        Location location = this.locationList.get(position);

        this.idVehicule         = convertView.findViewById(R.id.ligne_id_vehicule);
        this.date_debut         = convertView.findViewById(R.id.ligne_date_debut);
        this.date_fin           = convertView.findViewById(R.id.ligne_date_fin);
        this.tarif_journalier   = convertView.findViewById(R.id.ligne_tarif_journalier);

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        this.idVehicule.setText("#" + String.valueOf(location.getVehicule().getId()));
        this.date_debut.setText(format.format(location.getDate_debut()));
        this.date_fin.setText(format.format(location.getDate_fin()));
        this.tarif_journalier.setText(String.valueOf(location.getTarif_journalier()) + " €/J");

        return convertView;
    }
}
