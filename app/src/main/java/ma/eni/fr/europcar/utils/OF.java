package ma.eni.fr.europcar.utils;

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
}