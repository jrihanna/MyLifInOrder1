package com.example.mylifeinorder1.activity;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.core.view.ViewCompat;

import com.example.mylifeinorder1.model.Address;
import com.example.mylifeinorder1.model.Residence;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class HistoryWithAddressActivity extends HistoryActivity {
    private static int counter = 0;
    protected LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
    );

    @Override
    public LinearLayout createLayout() {
        params.setMargins(10,0,0,0);

        GradientDrawable border = new GradientDrawable();
        border.setColor(0xFFFFFFFF); //white background
        border.setStroke(1, 0xFF000000); //black border with full opacity

        LinearLayout c = new LinearLayout(this);
        c.setOrientation(LinearLayout.VERTICAL);
        c.setId(counter++);
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            c.setBackgroundDrawable(border);
        } else {
            c.setBackground(border);
        }

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

    protected Address getAddress(LinearLayout addressLayout) {
        Address address = new Address();
        address.setCountry(getLayoutEditTextValue(addressLayout, 0));
        address.setStreet(getLayoutEditTextValue(addressLayout, 1));
        address.setLine2(getLayoutEditTextValue(addressLayout, 2));
        address.setSuburb(getLayoutEditTextValue(addressLayout, 3));
        address.setState(getLayoutEditTextValue(addressLayout, 4));

        if(!getLayoutEditTextValue(addressLayout, 5).isEmpty())
            address.setPostCode(Integer.parseInt(getLayoutEditTextValue(addressLayout, 5)));

        return address;
    }
}
