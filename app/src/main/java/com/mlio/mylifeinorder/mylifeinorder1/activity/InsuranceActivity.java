package com.mlio.mylifeinorder.mylifeinorder1.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mlio.mylifeinorder.mylifeinorder1.R;
import com.mlio.mylifeinorder.mylifeinorder1.activity.adapter.InsuranceAdapter;
import com.mlio.mylifeinorder.mylifeinorder1.model.Insurance;
import com.mlio.mylifeinorder.mylifeinorder1.util.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InsuranceActivity extends HistoryActivity {
    List<Insurance> insuranceList;

    private InsuranceAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);

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
        insuranceList.add(new Insurance(0, "", 0, BigDecimal.ZERO));
        mAdapter.notifyItemInserted(insuranceList.size());
    }

    private void buildRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new InsuranceAdapter(insuranceList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnDeleteItemClickListener(position -> {
            insuranceList.remove(position);
            mAdapter.notifyItemRemoved(position);
        });
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(Constants.SHARED_PREFERENCE_INSURANCE, null);
        Type type = new TypeToken<ArrayList<Insurance>>() {}.getType();
        this.insuranceList = gson.fromJson(json, type);

        if (this.insuranceList == null) {
            this.insuranceList = new ArrayList<>();
        }
    }
    private void saveData() {
        if(this.insuranceList == null)
            this.insuranceList = new ArrayList<>();

        if(this.insuranceList.size() > 0 && !mAdapter.validate(findViewById(R.id.recyclerView)))
            return;

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this.insuranceList);
        editor.putString(Constants.SHARED_PREFERENCE_INSURANCE, json);
        editor.apply();

        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
    }
}
