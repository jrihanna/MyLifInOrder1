package com.example.mylifeinorder1.activity;

import android.widget.EditText;
import android.widget.LinearLayout;

public class EducationActivity extends HistoryWithAddressActivity {

    private LinearLayout createEducation() {
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

        LinearLayout schoolAddress = super.createLayout();

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
    }

}
