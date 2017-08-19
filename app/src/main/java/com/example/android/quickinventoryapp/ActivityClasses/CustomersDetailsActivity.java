package com.example.android.quickinventoryapp.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.quickinventoryapp.BaseClasses.CustomerInfo;
import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;

public class CustomersDetailsActivity extends AppCompatActivity {
    private TextView customerName,companyName,phone,email,addressLine1,addressLine2,city,zipCode;
    private DatabseHelper databseHelper;
    private String name ="",customercompanyName ="";
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        customerName =(TextView)findViewById(R.id.customernamedetailsTV);
        companyName = (TextView)findViewById(R.id.companyNameDetailsTV);
        phone =(TextView)findViewById(R.id.phoneTVdetails);
        email =(TextView)findViewById(R.id.emailTvdetails);
        addressLine1 =(TextView)findViewById(R.id.addressLine1TVdetails);
        addressLine2 =(TextView)findViewById(R.id.addressLine2TVdetails);
        city =(TextView)findViewById(R.id.cityTVdetails);
        zipCode =(TextView)findViewById(R.id.zipCodeTVdetails);
        deleteButton = (Button)findViewById(R.id.deleteCustomerbtn);
        Intent intent = getIntent();
        final String[] customerData =  intent.getExtras().getStringArray("data");
        databseHelper = new DatabseHelper(this);
        showcustomerInfo(customerData[0],customerData[1]);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int id = databseHelper.deleteACustomer(customerData[0],customerData[1]);
                Intent intent1 = new Intent(CustomersDetailsActivity.this,CustomersActivity.class);

                startActivity(intent1);

            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        finish();
        return  true;
    }

    public void showcustomerInfo(String cutomer,String company){
       CustomerInfo customerInfo = databseHelper.getCustomer(cutomer,company);
        customerName.setText(customerInfo.getCutomerName());
        companyName.setText(customerInfo.getCompanyName());
        phone.setText(customerInfo.getPhone());
        email.setText(customerInfo.getEmail());
        addressLine1.setText(customerInfo.getAddressLine1());
        addressLine2.setText(customerInfo.getGetAddressLine2());
        city.setText(customerInfo.getCity());
        zipCode.setText(customerInfo.getZipCode());
    }

}
