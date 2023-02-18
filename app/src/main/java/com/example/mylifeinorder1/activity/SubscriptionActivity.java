package com.example.mylifeinorder1.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylifeinorder1.R;

public class SubscriptionActivity extends HistoryActivity {

    @Override
    public LinearLayout createLayout() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10,0,0,0);

        LinearLayout c = new LinearLayout(this);
        c.setOrientation(LinearLayout.VERTICAL);

        EditText name = new EditText(this);
        name.setLayoutParams(params);
        name.setHint("Name");

        EditText fee = new EditText(this);
        fee.setLayoutParams(params);
        fee.setHint("Fee");

        // TODO: switch to drop down list
        EditText per = new EditText(this);
        per.setLayoutParams(params);
        per.setHint("Per");


        c.addView(fee);
        c.addView(per);
        c.addView(createDates(this));

        return c;
    }
}
