package com.example.mylifeinorder1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylifeinorder1.R;

public class AddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout currentLayout = findViewById(R.id.mainContainer);
        currentLayout.addView(createAddress());

        Button addAddress = findViewById(R.id.addAddress);
        addAddress.setOnClickListener(view -> {
            //TODO Add separator
            currentLayout.addView(createAddress());
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

}
