package com.example.mylifeinorder1.activity;

import android.widget.EditText;
import android.widget.LinearLayout;

public class HistoryWithAddressActivity extends HistoryActivity {
    protected LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
    );

    @Override
    public LinearLayout createLayout() {
        params.setMargins(10,0,0,0);

        LinearLayout c = new LinearLayout(this);
        c.setOrientation(LinearLayout.VERTICAL);

        EditText country = new EditText(this);
        country.setLayoutParams(params);
        country.setHint("Country");

        EditText street = new EditText(this);
        street.setLayoutParams(params);
        street.setHint("Street");

        EditText line2 = new EditText(this);
        line2.setLayoutParams(params);
        line2.setHint("Line 2");

        EditText suburb = new EditText(this);
        suburb.setLayoutParams(params);
        suburb.setHint("Suburb");

        EditText state = new EditText(this);
        state.setLayoutParams(params);
        state.setHint("State");

        EditText postCode = new EditText(this);
        postCode.setLayoutParams(params);
        postCode.setHint("Post Code");


        c.addView(country);
        c.addView(street);
        c.addView(line2);
        c.addView(suburb);
        c.addView(state);
        c.addView(postCode);
        c.addView(createDates(this));

        return c;
    }
}
