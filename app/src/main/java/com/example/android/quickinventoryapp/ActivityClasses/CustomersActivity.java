package com.example.android.quickinventoryapp.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.quickinventoryapp.BaseClasses.CustomerInfo;
import com.example.android.quickinventoryapp.Custom.CustomerAdapter;
import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;

import java.util.ArrayList;
import java.util.List;

public class CustomersActivity extends AppCompatActivity {
    ListView customersList;
    DatabseHelper databseHelper;
    FloatingActionButton fab;
    Intent intent;
    String btnName;

    static String USERNAME = "UserName";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        Intent intent = getIntent();
        String u_name = null;
        try {
            u_name =  intent.getStringExtra(DashBoardActivity.USERNAME).toString();
            Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Toast.makeText(this, btnName, Toast.LENGTH_SHORT).show();
        customersList = (ListView) findViewById(R.id.cutomersLV);

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.plus);

        final String finalU_name = u_name;

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomersActivity.this,CustomersAddActivity.class);
                intent.putExtra(USERNAME,finalU_name);
                startActivity(intent);
                finish();
            }
        });



        databseHelper = new DatabseHelper(this);
        try {
            final List<CustomerInfo> customerInfo = databseHelper.getAllCustomerInfo(u_name);


        CustomerAdapter adapter = new CustomerAdapter(this,getcustomersName(customerInfo),getCompanyName(customerInfo));
        customersList.setAdapter(adapter);


        customersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(CustomersActivity.this,CustomersDetailsActivity.class);
                CustomerInfo info = customerInfo.get(position);
                String[] data = {info.getCutomerName(),info.getCompanyName(), finalU_name};
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });}catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }


    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        finish();
        return  true;
    }




    List<String> getcustomersName(List<CustomerInfo> customerInfos){
        List<String> customersName = new ArrayList<>();
        for(int i=0;i<customerInfos.size();i++){
            customersName.add(customerInfos.get(i).getCutomerName());
        }

        return customersName;
    }

    List<String> getCompanyName(List<CustomerInfo> customerInfos){
        List<String> companyNames = new ArrayList<>();
        for(int i=0;i<customerInfos.size();i++){
            companyNames.add(customerInfos.get(i).getCompanyName());
        }

        return companyNames;
    }
}
