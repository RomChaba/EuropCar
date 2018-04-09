package ma.eni.fr.europcar.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ma.eni.fr.europcar.R;

public class ParametresAgenceFragment extends Fragment
{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ParametresListener mListener;
    private EditText saisonSociale;
    private EditText siret;
    private EditText voie;
    private EditText codePostal;
    private EditText ville;

    public ParametresAgenceFragment()
    {

    }

    public static ParametresAgenceFragment newInstance(String param1, String param2)
    {
        ParametresAgenceFragment fragment = new ParametresAgenceFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_parametres_agence, container, false);



        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        if (context instanceof ParametresListener)
        {
            mListener = (ParametresListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();

        mListener = null;
    }

    public interface ParametresListener
    {

    }
}