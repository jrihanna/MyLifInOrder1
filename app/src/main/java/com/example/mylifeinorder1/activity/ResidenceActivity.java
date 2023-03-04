package com.example.mylifeinorder1.activity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylifeinorder1.R;
import com.example.mylifeinorder1.activity.adapter.ResidenceAdapter;
import com.example.mylifeinorder1.model.Address;
import com.example.mylifeinorder1.model.History;
import com.example.mylifeinorder1.model.LioItem;
import com.example.mylifeinorder1.model.Residence;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ResidenceActivity extends AppCompatActivity {

    List<Residence> residenceList;

    private RecyclerView mRecyclerView;
    private ResidenceAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

//    @Override
    public void saveLayout() {
        LinearLayout currentLayout = findViewById(R.id.itemContainer);
        int childCount = currentLayout.getChildCount();

        List<Residence> residences = new ArrayList<>();

        for(int i = 0; i < childCount; i++) {
            View v = currentLayout.getChildAt(i);

            if(v instanceof LinearLayout) {
                LinearLayout addressLayout = (LinearLayout)v;
//                Residence residence = new Residence(getAddress(addressLayout));

                LinearLayout dates = (LinearLayout)((LinearLayout) v).getChildAt(6);
//                residence.setFromDate(getDates(dates, 0));
//                residence.setToDate(getDates(dates, 1));
//
//                residences.add(residence);
            }
        }

        // TODO: save the list
//        saveData(residences, ActivityType.RESIDENCE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*mainContainerId = R.id.mainAddressContainer;
        mainViewId = R.layout.activity_address;
        super.onCreate(savedInstanceState);*/

        super.onCreate(savedInstanceState);

        // R.layout.activity_employment
        setContentView(R.layout.activity_address);

//        residenceList = new ArrayList<>();
//        residenceList.add(new Residence(new Address("werw", null, null, null, null, null)));

        loadData();
        buildRecyclerView();
        setInsertButton();

        Button buttonSave = findViewById(R.id.save_button);
        buttonSave.setOnClickListener(v -> saveData());

    }

    private void setInsertButton() {
        Button buttonInsert = findViewById(R.id.add_button);
        buttonInsert.setOnClickListener(v -> {
            insertItem();
        });
    }

    private void insertItem() {
        residenceList.add(new Residence(new Address()));
        mAdapter.notifyItemInserted(residenceList.size());
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ResidenceAdapter(residenceList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("lifeInOrder4", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("address list", null);
        Type type = new TypeToken<ArrayList<Residence>>() {}.getType();
        this.residenceList = gson.fromJson(json, type);

        if (this.residenceList == null) {
            this.residenceList = new ArrayList<>();
        }
    }
    private void saveData() {
        mAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences = getSharedPreferences("lifeInOrder3", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this.residenceList);
        editor.putString("address list", json);
        editor.apply();
    }

}
