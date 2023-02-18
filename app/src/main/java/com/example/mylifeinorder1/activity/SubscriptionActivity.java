package com.example.mylifeinorder1.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylifeinorder1.R;

public class SubscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout currentLayout = findViewById(R.id.mainContainer);
//        currentLayout.addView(createEducation());

        Button addAddress = findViewById(R.id.addAddress);
        addAddress.setOnClickListener(view -> {
            //TODO Add separator
//            currentLayout.addView(createEducation());
        });
    }
}
