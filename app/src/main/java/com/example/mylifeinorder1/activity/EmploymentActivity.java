package com.example.mylifeinorder1.activity;

import android.widget.EditText;
import android.widget.LinearLayout;

public class EmploymentActivity extends HistoryWithAddressActivity {

    @Override
    public LinearLayout createLayout() {

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

        LinearLayout companyAddress = super.createLayout();


        c.addView(companyName);
        c.addView(role);
        c.addView(companyEmail);
        c.addView(companyPhone);
        c.addView(companyAddress);

        return c;
    }
}
