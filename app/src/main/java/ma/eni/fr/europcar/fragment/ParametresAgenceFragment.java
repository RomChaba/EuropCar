package ma.eni.fr.europcar.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.enums.Message;
import ma.eni.fr.europcar.model.Agence;
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
    private Agence agence;

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

    public void refreshAgence(Agence agence)
    {
        this.agence = agence;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_parametres_agence, container, false);

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
                    raisonSociale.setError(OF.getStringByName(view, Message.RAISON_SOCIALE_NON_RENSEIGNE));
                    erreur = true;
                }
                if(OF.isEditTextEmpty(siret))
                {
                    siret.setError(OF.getStringByName(view, Message.SIRET_NON_RENSEIGNE));
                    erreur = true;
                }
                else
                {
                    if(!OF.isSiretValide(OF.getTextFromEditText(siret)))
                    {
                        siret.setError(OF.getStringByName(view, Message.SIRET_ERREUR));
                        erreur = true;
                    }
                }
                if(OF.isEditTextEmpty(voie))
                {
                    voie.setError(OF.getStringByName(view, Message.VOIE_NON_RENSEIGNE));
                    erreur = true;
                }
                if(OF.isEditTextEmpty(codePostal))
                {
                    codePostal.setError(OF.getStringByName(view, Message.CODE_POSTAL_NON_RESEIGNE));
                    erreur = true;
                }
                else
                {
                    String codePostalActuel = OF.getTextFromEditText(codePostal);
                    if(codePostalActuel.length() != 5)
                    {
                        codePostal.setError(OF.getStringByName(view, Message.CODE_POSTAL_ERREUR));
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
                            siret.setError(OF.getStringByName(view, Message.CODE_POSTAL_ERREUR));
                            erreur = true;
                        }
                    }
                }
                if(OF.isEditTextEmpty(ville))
                {
                    ville.setError(OF.getStringByName(view, Message.VILLE_NON_RENSEIGNE));
                    erreur = true;
                }

                if(!erreur)
                {
                    if(mListener != null)
                    {
                        agence.setRaisonSociale(OF.getTextFromEditText(raisonSociale));
                        agence.setSiret(OF.getTextFromEditText(siret));
                        agence.setVoie(OF.getTextFromEditText(voie));
                        agence.setCodePostal(OF.getTextFromEditText(codePostal));
                        agence.setVille(OF.getTextFromEditText(ville));
                        mListener.parametresAgenceValide(agence);
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
        void parametresAgenceValide(Agence agence);
    }
}