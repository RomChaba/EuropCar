package ma.eni.fr.europcar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.adapter.VehiculeAdapteur;
import ma.eni.fr.europcar.model.Vehicule;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VehiculeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VehiculeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VehiculeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static List<Vehicule> vehiculeList = new ArrayList<>();
    private ListView listeVehicule;


    private OnFragmentInteractionListener mListener;

    public VehiculeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VehiculeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VehiculeFragment newInstance(String param1, String param2) {
        VehiculeFragment fragment = new VehiculeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_vehicule, container, false);
        this.listeVehicule = v.findViewById(R.id.liste_reservation);

        listeVehicule.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mListener.onVehiculeClick((Vehicule) listeVehicule.getItemAtPosition(position));
            }
        });


        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement LocationListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onVehiculeClick(Vehicule vehicule);
    }

    public void refreshList(List<Vehicule> vehiculeList){
        this.vehiculeList = vehiculeList;
        ArrayAdapter adapter = new VehiculeAdapteur((Context) mListener,R.layout.ligne_vehicule_liste,vehiculeList);

        this.listeVehicule = this.getView().findViewById(R.id.liste_reservation);

        this.listeVehicule.setAdapter(adapter);

    }
}
