package ma.eni.fr.europcar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.model.Vehicule;

/**
 * Created by Romain on 09/04/2018.
 */

public class VehiculeAdapteur extends ArrayAdapter<Vehicule> {

    private Context context;
    private int layout;
    private List<Vehicule> vehiculeList;

    //Commun
    private TextView idVehicule;

    //Partie reservation
    private TextView libelle;
    private TextView nb_place;
    private TextView location_min;
    private TextView location_max;
    private TextView tarif_min;
    private TextView tarif_max;



    public VehiculeAdapteur(@NonNull Context context, int resource, @NonNull List<Vehicule> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.vehiculeList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(this.layout,parent,false);
        }

        Vehicule vehicule = this.vehiculeList.get(position);

        this.idVehicule     = convertView.findViewById(R.id.ligne_id_vehicule);
        this.libelle        = convertView.findViewById(R.id.ligne_libelle);
        this.nb_place       = convertView.findViewById(R.id.ligne_nbPlaces);
        this.location_min   = convertView.findViewById(R.id.ligne_location_min);
        this.location_max   = convertView.findViewById(R.id.ligne_location_max);
        this.tarif_min      = convertView.findViewById(R.id.ligne_tarif_min);
        this.tarif_max      = convertView.findViewById(R.id.ligne_tarif_max);

        this.idVehicule.setText(String.valueOf(vehicule.getId()));
        this.libelle.setText(vehicule.getLibelle());
        this.nb_place.setText(String.valueOf(vehicule.getNbPlaces()));
        this.location_min.setText(String.valueOf(vehicule.getLocationMin()));
        this.location_max.setText(String.valueOf(vehicule.getLocationMax()));
        this.tarif_min.setText(String.valueOf(vehicule.getTarifMin()));
        this.tarif_max.setText(String.valueOf(vehicule.getTarifMax()));

        return convertView;
    }
}
