package com.example.android.quickinventoryapp.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.quickinventoryapp.Custom.AnalysisCategoryAdapter;
import com.example.android.quickinventoryapp.R;

import java.util.ArrayList;
import java.util.List;

public class AnalysisActivity extends AppCompatActivity {

    ListView ananlysicCategoryLV;
    static String USERNAME = "UserName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        String u_name = null;
        try {
            u_name =  intent.getStringExtra(DashBoardActivity.USERNAME).toString();
          //  Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
           // Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }


        ananlysicCategoryLV = (ListView)findViewById(R.id.analysisCategoryLV);

        final List<String> categories = new ArrayList<>();
        categories.add("Analysis of your Shop products Based on last 30 days");
        AnalysisCategoryAdapter adapter = new AnalysisCategoryAdapter(this,categories);
        ananlysicCategoryLV.setAdapter(adapter);

        final String finalU_name = u_name;
        ananlysicCategoryLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(AnalysisActivity.this, ChartActivity.class);
                    intent.putExtra(USERNAME, finalU_name);
                    startActivity(intent);
                }
            }
        });



    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        finish();
        return  true;
    }



}
