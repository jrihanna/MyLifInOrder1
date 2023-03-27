package com.mlio.mylifeinorder.mylifeinorder1.util;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.EditText;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class ViewUtil {


    public static String getLayoutEditTextValue(EditText t) {
        return t.getText() == null ? "" : t.getText().toString().trim();
    }

    public static String getLayoutEditTextValue(String t) {
        return t == null ? "" : t.trim();
    }

    public static LocalDate getDate(EditText t) {

        String date = getLayoutEditTextValue(t);
        return getDate(date);
    }

    public static BigDecimal getLayoutEditTextValueToBigDecimal(String t) {
        return t == null ? BigDecimal.ZERO :
                t.trim().length() == 0 ? BigDecimal.ZERO : new BigDecimal(t.trim());
    }

    public static LocalDate getDate(String date) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

            if(!date.isEmpty())
                return LocalDate.parse(date, formatter);
        }

        return null;
    }

    public static void showDateOnSelect(EditText dateView, Context context) {
        Calendar myCalendar= Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);

            String myFormat="dd/MM/yy";
            SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
            dateView.setText(dateFormat.format(myCalendar.getTime()));
        };
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(context,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public static void addClearButtonListener() {

    }
}
