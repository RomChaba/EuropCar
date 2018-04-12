package ma.eni.fr.europcar.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import ma.eni.fr.europcar.R;
import ma.eni.fr.europcar.enums.Message;
import ma.eni.fr.europcar.model.Retour;
import ma.eni.fr.europcar.utils.OF;

import static android.app.Activity.RESULT_OK;

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
    private Uri file = null;

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


        this.ajouterPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Vérifier les permissions
                if(ContextCompat.checkSelfPermission((Context) mListener, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    // La permissions doit être demandée
                    ActivityCompat.requestPermissions((Activity) mListener, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 42);
                }
                else
                {
                    // La permission a déjà été donnée, Lancer l'appareil photo
                    takePicture();
                }

            }
        });




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
                        if (file != null){
                            retour.setPhoto(file.toString());
                        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 42){
            if (grantResults.length >= 3
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED
                    && grantResults[2] == PackageManager.PERMISSION_GRANTED
                    )
            {takePicture();
            }else{
                Toast.makeText((Context) mListener, "Merci de nous laisser vous voler vos données personnelles", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"EuropCarTP");

        //vérifier Permissions
        if (ContextCompat.checkSelfPermission((Context) mListener, Manifest.permission.CAMERA )!= PackageManager.PERMISSION_GRANTED){
            //La permission doit etre demander
            ActivityCompat.requestPermissions(this.getActivity(),
                    new String[]{
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                    },
                    42);
        }

        if (!mediaStorageDir.exists()){
            mediaStorageDir.mkdir();
        }
        String timesStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        file = Uri.fromFile(new File(mediaStorageDir.getPath()+File.separator+timesStamp+".jpg"));

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        intent.putExtra(MediaStore.EXTRA_OUTPUT,file);
        startActivityForResult(intent,42);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 42){
            if (resultCode == RESULT_OK){
                Toast.makeText((Context) mListener, "Photo enregistée", Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText((Context) mListener, "La photo n'a pas été prise", Toast.LENGTH_SHORT).show();
            }
        }
    }




}