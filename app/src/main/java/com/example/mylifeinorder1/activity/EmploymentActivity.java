package com.example.mylifeinorder1.activity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.mylifeinorder1.R;
import com.example.mylifeinorder1.model.Employment;
import com.example.mylifeinorder1.model.Residence;

import java.util.ArrayList;
import java.util.List;

public class EmploymentActivity extends HistoryWithAddressActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainContainerId = R.id.mainEmploymentContainer;
        mainViewId = R.layout.activity_employment;
        super.onCreate(savedInstanceState);
    }

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
        companyEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

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
        LinearLayout currentLayout = findViewById(R.id.itemContainer);
        int childCount = currentLayout.getChildCount();

        List<Employment> employments = new ArrayList<>();

        // skip the buttons
        for(int i = 0; i < childCount; i++) {
            View v = currentLayout.getChildAt(i);

            if(v instanceof LinearLayout) {
                LinearLayout employmentLayout = (LinearLayout) v;
                Employment employment = new Employment();
                employment.setName(getLayoutEditTextValue(employmentLayout, 0));
                employment.setRole(getLayoutEditTextValue(employmentLayout, 1));
                employment.setEmailAddress(getLayoutEditTextValue(employmentLayout, 2));

                // TODO: format phone number
                employment.setNumber(getLayoutEditTextValue(employmentLayout, 3));

                LinearLayout addressLayout = (LinearLayout) employmentLayout.getChildAt(4);
                employment.setAddress(getAddress(addressLayout));

                LinearLayout dates = (LinearLayout)(addressLayout).getChildAt(6);
                employment.setFromDate(getDates(dates, 0));
                employment.setToDate(getDates(dates, 1));

                employments.add(employment);
            }
        }

        System.out.println(employments);
    }
}
