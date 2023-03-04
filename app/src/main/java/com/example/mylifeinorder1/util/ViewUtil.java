package com.example.mylifeinorder1.util;

import android.os.Build;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ViewUtil {

    public static String getLayoutEditTextValue(EditText t) {
        return t.getText() == null ? "" : t.getText().toString().trim();
    }

    public static LocalDate getDate(EditText t) {

        String date = getLayoutEditTextValue(t);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

            if(!date.isEmpty())
                return LocalDate.parse(date, formatter);
        }

        return null;
    }
}
