package com.example.mylifeinorder1.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.example.mylifeinorder1.R;
import com.example.mylifeinorder1.model.Address;
import com.example.mylifeinorder1.model.Residence;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ResidenceActivity extends HistoryWithAddressActivity {
    @Override
    public void saveLayout() {
        LinearLayout currentLayout = findViewById(R.id.itemContainer);
        int childCount = currentLayout.getChildCount();

        List<Residence> residences = new ArrayList<>();

        // skip the buttons
        for(int i = 0; i < childCount; i++) {
            View v = currentLayout.getChildAt(i);

            if(v instanceof LinearLayout) {
                LinearLayout addressLayout = (LinearLayout)v;
                Residence residence = new Residence(getAddress(addressLayout));

                LinearLayout dates = (LinearLayout)((LinearLayout) v).getChildAt(6);
                residence.setFromDate(getDates(dates, 0));
                residence.setToDate(getDates(dates, 1));

                residences.add(residence);
            }
        }

        // TODO: save the list
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainContainerId = R.id.mainAddressContainer;
        mainViewId = R.layout.activity_address;
        super.onCreate(savedInstanceState);
    }


}
