package com.example.mylifeinorder1.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylifeinorder1.R;

public class EmploymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout currentLayout = findViewById(R.id.mainContainer);
        currentLayout.addView(createEmployment());

        Button addAddress = findViewById(R.id.addAddress);
        addAddress.setOnClickListener(view -> {
            //TODO Add separator
            currentLayout.addView(createEmployment());
        });
    }

    private LinearLayout createAddress() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
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

        return c;
    }

    private LinearLayout createEmployment() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10,0,0,0);

        LinearLayout c = new LinearLayout(this);
        c.setOrientation(LinearLayout.VERTICAL);

        EditText companyName = new EditText(this);
        companyName.setLayoutParams(params);
        companyName.setHint("Company Name");

        EditText companyEmail = new EditText(this);
        companyEmail.setLayoutParams(params);
        companyEmail.setHint("Company Email");

        EditText companyPhone = new EditText(this);
        companyPhone.setLayoutParams(params);
        companyPhone.setHint("Company Phone");

        LinearLayout companyAddress = createAddress();

        c.addView(companyName);
        c.addView(companyEmail);
        c.addView(companyPhone);
        c.addView(companyAddress);

        return c;
    }
}
