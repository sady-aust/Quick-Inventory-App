package com.example.android.quickinventoryapp.ActivityClasses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
    String u_name = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hsitory);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        historyLV = (ListView) findViewById(R.id.historyLV);
        databseHelper = new DatabseHelper(this);
        Intent intent = getIntent();

        try {
            u_name =  intent.getStringExtra(DashBoardActivity.USERNAME).toString();
           // Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
           // Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }

        List<HistoryInfo> history = databseHelper.getAllHsitory(u_name);

        //List<String> notifications = getNotification(history);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notifications);

        HistoryAdapter adapter = new HistoryAdapter(this,getAllDate(history),getAllCustomerName(history),getAllProductName(history),getAllPrices(history));
        historyLV.setAdapter(adapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }



    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_catalog,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.allDeleteHistory:
                deleteAll(u_name);


        }

        return super.onOptionsItemSelected(item);
    }

    public void deleteAll(final String uName){
        AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
        builder.setTitle("Message");
        builder.setMessage("All History will be Deleted?");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                databseHelper.deleteAllHistory(uName);

                List<HistoryInfo> historyListU =    databseHelper.getAllHsitory(u_name);
                HistoryAdapter adapter = new HistoryAdapter(HistoryActivity.this,getAllDate(historyListU),getAllCustomerName(historyListU),getAllProductName(historyListU),getAllPrices(historyListU));
                historyLV.setAdapter(adapter);
                Toast.makeText(HistoryActivity.this,"Successfully Deleted",Toast.LENGTH_LONG).show();


            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

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
