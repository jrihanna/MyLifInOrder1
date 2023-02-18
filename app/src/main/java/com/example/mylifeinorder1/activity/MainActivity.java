package com.example.mylifeinorder1.activity;

import androidx.annotation.IntDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mylifeinorder1.R;

public class MainActivity extends HistoryWithAddressActivity {

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

    /*private LinearLayout createAddress() {
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
        c.addView(createDates(this));

        return c;
    }*/

    /*private LinearLayout createEmployment() {
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

    private LinearLayout createEducation() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10,0,0,0);

        LinearLayout c = new LinearLayout(this);
        c.setOrientation(LinearLayout.VERTICAL);

        EditText schoolName = new EditText(this);
        schoolName.setLayoutParams(params);
        schoolName.setHint("School Name");

        EditText schoolEmail = new EditText(this);
        schoolEmail.setLayoutParams(params);
        schoolEmail.setHint("School Email");

        EditText schoolPhone = new EditText(this);
        schoolPhone.setLayoutParams(params);
        schoolPhone.setHint("School Phone");

        LinearLayout schoolAddress = createAddress();

        EditText fieldOfStudy = new EditText(this);
        fieldOfStudy.setLayoutParams(params);
        fieldOfStudy.setHint("Field of Study");

        EditText grade = new EditText(this);
        grade.setLayoutParams(params);
        grade.setHint("Grade");

        c.addView(schoolName);
        c.addView(schoolEmail);
        c.addView(schoolPhone);
        c.addView(schoolAddress);
        c.addView(fieldOfStudy);
        c.addView(grade);

        return c;
    }*/
}