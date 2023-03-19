package com.mlio.mylifeinorder.mylifeinorder1.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mlio.mylifeinorder.mylifeinorder1.R;
import com.mlio.mylifeinorder.mylifeinorder1.activity.adapter.EmploymentAdapter;
import com.mlio.mylifeinorder.mylifeinorder1.model.Address;
import com.mlio.mylifeinorder.mylifeinorder1.model.Employment;
import com.mlio.mylifeinorder.mylifeinorder1.util.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EmploymentActivity extends AppCompatActivity {

    List<Employment> employmentList;

    private EmploymentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employment);

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
        employmentList.add(new Employment("", "", "", "", new Address()));
        mAdapter.notifyItemInserted(employmentList.size());
    }

    private void buildRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new EmploymentAdapter(employmentList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnDeleteItemClickListener(position -> {
            employmentList.remove(position);
            mAdapter.notifyItemRemoved(position);
        });
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(Constants.SHARED_PREFERENCE_EMPLOYMENT, null);
        Type type = new TypeToken<ArrayList<Employment>>() {}.getType();
        this.employmentList = gson.fromJson(json, type);

        if (this.employmentList == null) {
            this.employmentList = new ArrayList<>();
        }
    }
    private void saveData() {
        if(this.employmentList == null)
            this.employmentList = new ArrayList<>();

        if(this.employmentList.size() > 0 && !mAdapter.validate(findViewById(R.id.recyclerView)))
            return;

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this.employmentList);
        editor.putString(Constants.SHARED_PREFERENCE_EMPLOYMENT, json);
        editor.apply();

        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
    }
}
