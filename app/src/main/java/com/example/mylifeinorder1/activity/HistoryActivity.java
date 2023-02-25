package com.example.mylifeinorder1.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

    protected int mainContainerId = 0;
    protected int mainViewId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // R.layout.activity_employment
        setContentView(mainViewId);

        // R.id.mainContainer
        LinearLayout currentLayout = findViewById(R.id.itemContainer);
        currentLayout.addView(createLayout());

        Button addLayout = findViewById(R.id.addButton);
        addLayout.setOnClickListener(view -> {
            currentLayout.addView(addSeparatorView());

            LinearLayout newLayout = createLayout();
            currentLayout.addView(newLayout);
            newLayout.addView(addRemoveButton());
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

    private Button addRemoveButton() {
        Button removeButton = new Button(this);
        removeButton.setText("Remove");
        LinearLayout.LayoutParams removeButtonLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        removeButton.setLayoutParams(removeButtonLayoutParams);
        removeButton.setOnClickListener(view -> {
            ((ViewGroup) view.getParent().getParent()).removeView((ViewGroup) view.getParent());
        });

        return removeButton;
    }
    private View addSeparatorView() {
        View separator = new View(this);
        LinearLayout.LayoutParams separatorLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                10);
        separatorLayoutParams.setMargins(0, 30, 0, 0);
        separator.setLayoutParams(separatorLayoutParams);
        separator.setBackgroundColor(Color.WHITE);

        return separator;
    }

}
