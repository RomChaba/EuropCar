package ma.eni.fr.europcar.utils;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import ma.eni.fr.europcar.enums.Message;
import ma.eni.fr.europcar.enums.TypeErreur;

/**
 * Created by Administrateur on 09/04/2018.
 */

public class OF
{
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
}