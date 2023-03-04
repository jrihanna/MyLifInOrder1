package com.example.mylifeinorder1.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.mylifeinorder1.model.History;
import com.example.mylifeinorder1.model.Residence;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public abstract class HistoryActivity extends AppCompatActivity {

    protected int mainContainerId = 0;
    protected int mainViewId = 0;

    /*@Override
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
            newLayout.addView(addRemoveButton());
            currentLayout.addView(newLayout);
        });

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(view -> {
            saveLayout();
        });

        List<History> df = loadData(ActivityType.RESIDENCE);
        System.out.println("ssfsff");
    }*/
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

    protected void saveData(List<? extends History> items, ActivityType type) {
        SharedPreferences sharedPreferences = getSharedPreferences("MLIO Data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        editor.putString(type.name(), json);
        editor.apply();
    }

    protected List<History> loadData(ActivityType activityType) {
        List<History> result = new ArrayList<>();

        SharedPreferences sharedPreferences = getSharedPreferences("MLIO Data", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(activityType.name(), null);
        Type type = new TypeToken<List<Residence>>() {}.getType();
        result = gson.fromJson(json, type);

        if (result == null) {
            result = new ArrayList<>();
        }

        return result;
    }

    private void updateLabel(Calendar calendar, EditText view){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        view.setText(dateFormat.format(calendar.getTime()));
    }

    protected Button addRemoveButton() {
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
    protected View addSeparatorView() {
        View separator = new View(this);
        LinearLayout.LayoutParams separatorLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        separatorLayoutParams.setMargins(0, 40, 0, 0);
        separator.setLayoutParams(separatorLayoutParams);
        separator.setBackgroundColor(Color.WHITE);

        return separator;
    }


}
