package ma.eni.fr.europcar.utils;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ma.eni.fr.europcar.enums.Message;
import ma.eni.fr.europcar.enums.TypeErreur;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class OF
{
    public static final String PATTERN_MAIL = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String PATTERN_SIRET = "^[0-9]{14}$";

    public static boolean isEditTextEmpty(EditText editText)
    {
        return (editText != null ? editText.getText().toString().trim().isEmpty() : false);
    }

    public static String getTextFromEditText(EditText editText)
    {
        return (editText != null ? editText.getText().toString().trim() : "");
    }

    public static int getStringByName(Context context, String name)
    {
        return context.getResources().getIdentifier(name, "string", context.getPackageName());
    }

    public static int getStringByName(Context context, TypeErreur name)
    {
        return getStringByName(context, name.name());
    }

    public static int getStringByName(Context context, Message name)
    {
        return getStringByName(context, name.name());
    }

    public static String getStringByName(View view, Message name)
    {
        return (String) view.getContext().getText(getStringByName(view.getContext(), name.name()));
    }

    public static String getIp(Context context)
    {
        return (String) context.getText(OF.getStringByName(context, "ipApi"));
    }

    public static String getApiError(ExecutionException e)
    {
        String result = "Erreur";

        if (VolleyError.class.isAssignableFrom(e.getCause().getClass()))
        {
            VolleyError ve = (VolleyError) e.getCause();
            if (ve.networkResponse != null)
            {
                try
                {
                    JSONObject error = new JSONObject(new String(ve.networkResponse.data));
                    result = error.getString("error");
                }
                catch (JSONException e1)
                {
                    e1.printStackTrace();
                }
            }
        }

        return result;
    }

    public static boolean checkRegexValidity(String pattern, String chaine)
    {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(chaine);

        if (m.find())
        {
            return true;
        }

        return false;
    }

    public static boolean isEmailValide(String email)
    {
        return !email.isEmpty() && checkRegexValidity(PATTERN_MAIL, email);
    }

    public static boolean isSiretValide(String siret)
    {
        return !siret.isEmpty() && checkRegexValidity(PATTERN_SIRET, siret);
    }
}