package com.example.android.quickinventoryapp.ActivityClasses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
 ListView historyLV;
    DatabseHelper databseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hsitory);
        historyLV = (ListView)findViewById(R.id.historyLV);
        databseHelper = new DatabseHelper(this);
       List<String> history = databseHelper.getAllHsitory();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,history);
        historyLV.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        finish();
        return  true;
    }
}
