package com.example.mylifeinorder1.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.mylifeinorder1.R;
import com.example.mylifeinorder1.model.Residence;

import java.util.ArrayList;
import java.util.List;

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


        c.addView(companyName);
        c.addView(role);
        c.addView(companyEmail);
        c.addView(companyPhone);

        LinearLayout companyAddress = super.createLayout();
        c.addView(companyAddress);

        return c;
    }

    @Override
    public void saveLayout() {
        LinearLayout currentLayout = findViewById(R.id.mainContainer);
        int childCount = currentLayout.getChildCount();

        List<Residence> employments = new ArrayList<>();

        // skip the buttons
        for(int i = 2; i < childCount; i++) {
            View v = currentLayout.getChildAt(i);

            if(v instanceof LinearLayout) {
                LinearLayout employment = (LinearLayout) v;

                // 0: company name
                // 1 role
                // 2: email
                // 3: phone
                // 4: address and dates
            }
        }
    }
}
