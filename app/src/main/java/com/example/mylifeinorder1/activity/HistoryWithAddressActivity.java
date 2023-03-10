package com.example.mylifeinorder1.activity;

import android.graphics.Color;
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


    protected Address getAddress(LinearLayout addressLayout) {
        Address address = new Address();
        address.setCountry(getLayoutEditTextValue(addressLayout, 0));
        address.setStreet(getLayoutEditTextValue(addressLayout, 1));
        address.setLine2(getLayoutEditTextValue(addressLayout, 2));
        address.setSuburb(getLayoutEditTextValue(addressLayout, 3));
        address.setState(getLayoutEditTextValue(addressLayout, 4));

        if(!getLayoutEditTextValue(addressLayout, 5).isEmpty())
            address.setPostCode(getLayoutEditTextValue(addressLayout, 5));

        return address;
    }
}
