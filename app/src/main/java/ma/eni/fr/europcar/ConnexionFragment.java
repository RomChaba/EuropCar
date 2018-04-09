package ma.eni.fr.europcar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Type;

import ma.eni.fr.europcar.enums.TypeAffichage;

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
    private TypeAffichage typeAffichage;

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

        this.inscription.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(mListener != null)
                {
                    mListener.redirectionVersInscription();
                }
            }
        });

        this.se_connecter.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

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

    public void setTypeAffichage(TypeAffichage typeAffichage)
    {
        this.typeAffichage = typeAffichage;

        if(typeAffichage != null)
        {
            if(TypeAffichage.CONNEXION == typeAffichage)
            {
                this.mot_de_passe2.setVisibility(View.GONE);
                this.s_inscrire.setVisibility(View.GONE);
            }
            else if(TypeAffichage.INSCRIPTION == typeAffichage)
            {
                this.se_connecter.setVisibility(View.GONE);
                this.inscription.setVisibility(View.GONE);
            }
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
        void redirectionVersInscription();

        void connexionValider();

        void inscriptionValider();
    }
}