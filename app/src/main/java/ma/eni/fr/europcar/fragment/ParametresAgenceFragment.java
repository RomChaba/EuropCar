package ma.eni.fr.europcar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.utils.OF;

public class ParametresAgenceFragment extends Fragment
{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ParametresListener mListener;
    private EditText raisonSociale;
    private EditText siret;
    private EditText voie;
    private EditText codePostal;
    private EditText ville;
    private Button modifier;

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

        this.raisonSociale = view.findViewById(R.id.parametres_agence_raison_sociale);
        this.siret = view.findViewById(R.id.parametres_agence_siret);
        this.voie = view.findViewById(R.id.parametres_agence_voie);
        this.codePostal = view.findViewById(R.id.parametres_agence_code_postal);
        this.ville = view.findViewById(R.id.parametres_agence_ville);
        this.modifier = view.findViewById(R.id.parametres_agence_modifier);

        this.modifier.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean erreur = false;

                if(OF.isEditTextEmpty(raisonSociale))
                {
                    raisonSociale.setError("Veuillez renseigner une raison sociale");
                    erreur = true;
                }
                if(OF.isEditTextEmpty(siret))
                {
                    siret.setError("Veuillez renseigner un numéro de SIRET");
                    erreur = true;
                }
                else
                {
                    String siretActuel = OF.getTextFromEditText(siret);
                    if(siretActuel.length() != 14)
                    {
                        siret.setError("Le numéro de SIRET doit être composé de 14 chiffres");
                        erreur = true;
                    }
                    else
                    {
                        try
                        {
                            Long.parseLong(siretActuel);
                        }
                        catch (ArithmeticException e)
                        {
                            siret.setError("Le numéro de SIRET doit être composé de 14 chiffres");
                            erreur = true;
                        }
                    }
                }
                if(OF.isEditTextEmpty(voie))
                {
                    voie.setError("Veuillez renseigner un numéro de voie");
                    erreur = true;
                }
                if(OF.isEditTextEmpty(codePostal))
                {
                    codePostal.setError("Veuillez renseigner un code postal");
                    erreur = true;
                }
                else
                {
                    String codePostalActuel = OF.getTextFromEditText(codePostal);
                    if(codePostalActuel.length() != 5)
                    {
                        codePostal.setError("Le code postal doit être composé de 5 chiffres");
                        erreur = true;
                    }
                    else
                    {
                        try
                        {
                            Integer.parseInt(codePostalActuel);
                        }
                        catch (ArithmeticException e)
                        {
                            siret.setError("Le code postal doit être composé de 5 chiffres");
                            erreur = true;
                        }
                    }
                }
                if(OF.isEditTextEmpty(ville))
                {
                    ville.setError("Veuillez renseigner un nom de ville");
                    erreur = true;
                }

                if(!erreur)
                {
                    if(mListener != null)
                    {
                        mListener.parametresAgenceValide(OF.getTextFromEditText(raisonSociale), OF.getTextFromEditText(siret), OF.getTextFromEditText(voie), OF.getTextFromEditText(codePostal), OF.getTextFromEditText(ville));
                    }
                }
            }
        });

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
            throw new RuntimeException(context.toString() + " must implement LocationListener");
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
        void parametresAgenceValide(String raisonSociale, String siret, String voie, String codePostal, String ville);
    }
}