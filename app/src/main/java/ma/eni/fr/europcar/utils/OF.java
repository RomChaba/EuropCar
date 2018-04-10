package ma.eni.fr.europcar.utils;

import android.content.Context;
import android.widget.EditText;

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
}