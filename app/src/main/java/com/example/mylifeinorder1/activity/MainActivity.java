package com.example.mylifeinorder1.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mylifeinorder1.R;
import com.example.mylifeinorder1.activity.adapter.LioAdapter;
import com.example.mylifeinorder1.model.LioItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private LioAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<LioItem> lioItems = new ArrayList<>();
        lioItems.add(new LioItem(R.drawable.house_svg, "History of Residence", ActivityType.RESIDENCE));
        lioItems.add(new LioItem(R.drawable.employment_svg, "History of Employment", ActivityType.EMPLOYMENT));
        lioItems.add(new LioItem(R.drawable.education_svg, "History of Education", ActivityType.EDUCATION));
        lioItems.add(new LioItem(R.drawable.insurance, "Insurances", ActivityType.INSURANCE));
        lioItems.add(new LioItem(R.drawable.loan_svg, "Loans", ActivityType.LOAN));

        buildRecyclerView(lioItems);


/*

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
    */
    }

    public void itemSelected(ActivityType activityType) {
        try {
            Intent k;
            switch (activityType) {
                case RESIDENCE:
                    k = new Intent(MainActivity.this, ResidenceActivity.class);
                    break;
                case EMPLOYMENT:
                    k = new Intent(MainActivity.this, EmploymentActivity.class);
                    break;
                case EDUCATION:
                    k = new Intent(MainActivity.this, EducationActivity.class);
                    break;
                case INSURANCE:
                    k = new Intent(MainActivity.this, InsuranceActivity.class);
                    break;
                default:
                    k = new Intent(MainActivity.this, ResidenceActivity.class);
            }

            startActivity(k);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void buildRecyclerView(List<LioItem> lioItems) {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new LioAdapter(lioItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new LioAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                itemSelected(lioItems.get(position).getActivityType());
            }
        });
    }
}