package com.example.mylifeinorder1.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylifeinorder1.R;

public class EmploymentActivity extends HistoryWithAddressActivity {

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

        EditText role = new EditText(this);
        role.setLayoutParams(params);
        role.setHint("Role");

        EditText companyEmail = new EditText(this);
        companyEmail.setLayoutParams(params);
        companyEmail.setHint("Company Email");

        EditText companyPhone = new EditText(this);
        companyPhone.setLayoutParams(params);
        companyPhone.setHint("Company Phone");

        LinearLayout companyAddress = createAddress();


        c.addView(companyName);
        c.addView(role);
        c.addView(companyEmail);
        c.addView(companyPhone);
        c.addView(companyAddress);

        return c;
    }
}
