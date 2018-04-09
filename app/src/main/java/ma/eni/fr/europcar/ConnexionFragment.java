package ma.eni.fr.europcar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ConnexionFragment extends Fragment
{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ConnexionListener mListener;

    private EditText email;
    private EditText mot_de_passe;
    private EditText mot_de_passe2;
    private Button se_connecter;
    private Button inscription;
    private Button s_inscrire;

    public ConnexionFragment()
    {

    }

    public static ConnexionFragment newInstance(String param1, String param2)
    {
        ConnexionFragment fragment = new ConnexionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_connexion, container, false);

        this.email = view.findViewById(R.id.connexion_email);
        this.mot_de_passe = view.findViewById(R.id.connexion_mot_de_passe);
        this.mot_de_passe2 = view.findViewById(R.id.connexion_mot_de_passe_2);
        this.se_connecter = view.findViewById(R.id.connexion_se_connecter);
        this.inscription = view.findViewById(R.id.connexion_inscription);
        this.s_inscrire = view.findViewById(R.id.connexion_s_inscrire);

        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        if (context instanceof ConnexionListener)
        {
            mListener = (ConnexionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement ConnexionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();

        mListener = null;
    }

    public interface ConnexionListener
    {

    }
}