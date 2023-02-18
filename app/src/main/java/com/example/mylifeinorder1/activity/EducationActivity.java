package com.example.mylifeinorder1.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylifeinorder1.R;

public class EducationActivity extends HistoryWithAddressActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout currentLayout = findViewById(R.id.mainContainer);
        currentLayout.addView(createEducation());

        Button addAddress = findViewById(R.id.addAddress);
        addAddress.setOnClickListener(view -> {
            //TODO Add separator
            currentLayout.addView(createEducation());
        });
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
    }

}
