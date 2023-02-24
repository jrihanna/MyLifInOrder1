package com.example.mylifeinorder1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mylifeinorder1.R;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout currentLayout = findViewById(R.id.mainContainer);
//        currentLayout.addView(createAddress());

        TextView residenceHistoryView = findViewById(R.id.residenceHistory);
        residenceHistoryView.setOnClickListener(view -> {
            try {
                Intent k = new Intent(MainActivity.this, ResidenceActivity.class);
                startActivity(k);
            } catch(Exception e) {
                e.printStackTrace();
            }
        });

        TextView employmentHistoryView = findViewById(R.id.employmentHistory);
        employmentHistoryView.setOnClickListener(view -> {
            try {
                Intent k = new Intent(MainActivity.this, EmploymentActivity.class);
                startActivity(k);
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
    }

}