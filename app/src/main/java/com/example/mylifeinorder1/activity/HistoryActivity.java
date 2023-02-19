package com.example.mylifeinorder1.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylifeinorder1.R;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public abstract class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout currentLayout = findViewById(R.id.mainContainer);
        currentLayout.addView(createLayout());

        Button addLayout = findViewById(R.id.addAddress);
        addLayout.setOnClickListener(view -> {
            //TODO Add separator
            currentLayout.addView(createLayout());
        });

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(view -> {
            saveLayout();
        });
    }
    public abstract LinearLayout createLayout();

    public abstract void saveLayout();

    protected LinearLayout createDates(Context context) {

        final Calendar calendar = Calendar.getInstance();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10,0,0,0);
        params.weight = 1;

        LinearLayout dc = new LinearLayout(this);
        dc.setOrientation(LinearLayout.HORIZONTAL);
        dc.setWeightSum(2f);

        EditText dateFrom = new EditText(context);
        dateFrom.setLayoutParams(params);
        dateFrom.setHint("From Date");
        DatePickerDialog.OnDateSetListener dateFromPicker = (view, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,day);
            updateLabel(calendar, dateFrom);
        };

        dateFrom.setOnClickListener(view -> {
            new DatePickerDialog(context,dateFromPicker,calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        EditText dateTo = new EditText(context);
        dateTo.setHint("To Date");
        dateTo.setLayoutParams(params);
        DatePickerDialog.OnDateSetListener dateToPicker = (view, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,day);
            updateLabel(calendar, dateTo);
        };

        dateTo.setOnClickListener(view -> {
            new DatePickerDialog(context,dateToPicker,calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        dc.addView(dateFrom);
        dc.addView(dateTo);

        return dc;
    }

    protected String getLayoutEditTextValue(LinearLayout layout, int childIndex) {
        EditText t = ((EditText)layout.getChildAt(childIndex));
        return t.getText() == null ? "" : t.getText().toString();
    }

    protected LocalDate getDates(LinearLayout datesLayout, int index) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            if(!getLayoutEditTextValue(datesLayout, index).isEmpty())
                return LocalDate.parse(getLayoutEditTextValue(datesLayout, 1), formatter);
        }

        return null;
    }
    private void updateLabel(Calendar calendar, EditText view){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        view.setText(dateFormat.format(calendar.getTime()));
    }
}
