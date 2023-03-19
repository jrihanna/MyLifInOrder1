package com.mlio.mylifeinorder.mylifeinorder1.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mlio.mylifeinorder.mylifeinorder1.R;
import com.mlio.mylifeinorder.mylifeinorder1.activity.adapter.SubscriptionAdapter;
import com.mlio.mylifeinorder.mylifeinorder1.model.Subscription;
import com.mlio.mylifeinorder.mylifeinorder1.util.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionActivity extends AppCompatActivity {
    List<Subscription> subscriptionList;

    private SubscriptionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

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
        subscriptionList.add(new Subscription(BigDecimal.ZERO, 0));
        mAdapter.notifyItemInserted(subscriptionList.size());
    }

    private void buildRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SubscriptionAdapter(subscriptionList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnDeleteItemClickListener(position -> {
            subscriptionList.remove(position);
            mAdapter.notifyItemRemoved(position);
        });
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(Constants.SHARED_PREFERENCE_SUBSCRIPTION, null);
        Type type = new TypeToken<ArrayList<Subscription>>() {}.getType();
        this.subscriptionList = gson.fromJson(json, type);

        if (this.subscriptionList == null) {
            this.subscriptionList = new ArrayList<>();
        }
    }

    private void saveData() {
        if(this.subscriptionList == null)
            this.subscriptionList = new ArrayList<>();

        if(this.subscriptionList.size() > 0 && !mAdapter.validate(findViewById(R.id.recyclerView)))
            return;

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this.subscriptionList);
        editor.putString(Constants.SHARED_PREFERENCE_SUBSCRIPTION, json);
        editor.apply();

        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
    }
}
