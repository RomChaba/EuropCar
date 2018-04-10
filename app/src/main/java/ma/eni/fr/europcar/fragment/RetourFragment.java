package ma.eni.fr.europcar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.enums.Message;
import ma.eni.fr.europcar.model.Retour;
import ma.eni.fr.europcar.utils.OF;

public class RetourFragment extends Fragment
{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RendreLocationListener mListener;
    private CheckBox estEndommagee;
    private CheckBox pleinFait;
    private EditText kms;
    private Button rendre;
    private Button ajouterPhoto;

    public RetourFragment()
    {

    }

    public static RetourFragment newInstance(String param1, String param2)
    {
        RetourFragment fragment = new RetourFragment();
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
        final View view = inflater.inflate(R.layout.fragment_retour, container, false);

        this.estEndommagee = view.findViewById(R.id.rendre_location_est_endommagee);
        this.pleinFait = view.findViewById(R.id.rendre_location_plein_fait);
        this.kms = view.findViewById(R.id.rendre_location_kms);
        this.rendre = view.findViewById(R.id.rendre_location_rendre);
        this.ajouterPhoto = view.findViewById(R.id.rendre_location_ajouter_photo);

        this.rendre.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean erreur = false;

                if(estEndommagee == null)
                {
                    estEndommagee.setError(OF.getStringByName(view, Message.EST_ENDOMMAGEE_NON_RENSEIGNE));
                    erreur = true;
                }
                if(pleinFait == null)
                {
                    pleinFait.setError(OF.getStringByName(view, Message.PLEIN_FAIT_NON_RENSEIGNE));
                    erreur = true;
                }
                if(OF.isEditTextEmpty(kms))
                {
                    kms.setError(OF.getStringByName(view, Message.KMS_NON_RENSEIGNE));
                    erreur = true;
                }
                else
                {
                    try
                    {
                        Integer.parseInt(OF.getTextFromEditText(kms));
                    }
                    catch (Exception e)
                    {
                        kms.setError(OF.getStringByName(view, Message.KMS_ERREUR));
                        erreur = true;
                    }
                }

                if(!erreur)
                {
                    if(mListener != null)
                    {
                        Retour retour = new Retour();
                        retour.setEndommage(estEndommagee.isChecked());
                        retour.setPleinEffectue(pleinFait.isChecked());
                        retour.setNbKmsEffectues(Integer.parseInt(OF.getTextFromEditText(kms)));
                        mListener.rendreLocation(retour);
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

        if (context instanceof RendreLocationListener)
        {
            mListener = (RendreLocationListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement RendreLocationListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();

        mListener = null;
    }

    public interface RendreLocationListener
    {
        void rendreLocation(Retour retour);
    }
}