package com.example.android.quickinventoryapp.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.quickinventoryapp.BaseClasses.HistoryInfo;
import com.example.android.quickinventoryapp.Custom.HistoryAdapter;
import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    ListView historyLV;
    DatabseHelper databseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hsitory);
        historyLV = (ListView) findViewById(R.id.historyLV);
        databseHelper = new DatabseHelper(this);
        Intent intent = getIntent();
        String u_name = null;
        try {
            u_name =  intent.getStringExtra(DashBoardActivity.USERNAME).toString();
            Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }

        List<HistoryInfo> history = databseHelper.getAllHsitory(u_name);

        //List<String> notifications = getNotification(history);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notifications);

        HistoryAdapter adapter = new HistoryAdapter(this,getAllDate(history),getAllCustomerName(history),getAllProductName(history),getAllPrices(history));
        historyLV.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private List<String> getNotification(List<HistoryInfo> history) {
       List<String> note = new ArrayList<>();

        for(int i=0;i<history.size();i++){
           HistoryInfo historyInfo = history.get(i);
            String notification = "You sell "+historyInfo.getQuantity()+" "+historyInfo.getProductName()+" on "+historyInfo.getDate();
            note.add(notification);
        }
        return note;
    }


    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        finish();
        return true;
    }

    public List<String> getAllCustomerName(List<HistoryInfo> historyInfos){
        List<String> names = new ArrayList<>();

        for(HistoryInfo history: historyInfos){
            names.add(history.getCustomerName());
        }

        return names;
    }

    public List<String> getAllProductName(List<HistoryInfo> historyInfos){
        List<String> names = new ArrayList<>();

        for(HistoryInfo history: historyInfos){
            names.add(history.getProductName());
        }

        return names;
    }

    public List<String> getAllPrices(List<HistoryInfo> historyInfos){
        List<String> prices = new ArrayList<>();

        for(HistoryInfo history: historyInfos){
            prices.add(history.getTotalPrice());
        }

        return prices;
    }

    public List<String> getAllDate(List<HistoryInfo> historyInfos){
        List<String> dates = new ArrayList<>();

        for(HistoryInfo history: historyInfos){
            dates.add(history.getDate());
        }

        return dates;
    }




}
