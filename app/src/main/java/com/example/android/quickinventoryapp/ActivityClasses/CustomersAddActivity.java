package com.example.android.quickinventoryapp.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.quickinventoryapp.BaseClasses.CustomerInfo;
import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;

public class CustomersAddActivity extends AppCompatActivity {
    EditText customerNameE,companyNameE,phoneE,emailE,addressLine1E,addressLine2E,cityE,zipCodeE;
    Button save;
    DatabseHelper databseHelper;
    String u_name ="";
    static String USERNAME = "UserName";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databseHelper = new DatabseHelper(this);
        setContentView(R.layout.activity_cusromars_add);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        customerNameE = (EditText)findViewById(R.id.castomrNameET);
        companyNameE = (EditText)findViewById(R.id.companyNameET);
        phoneE = (EditText)findViewById(R.id.phoneET);
        emailE = (EditText)findViewById(R.id.emailET);
        addressLine1E = (EditText)findViewById(R.id.addressLineOneET);
        addressLine2E = (EditText)findViewById(R.id.addressLineTwoET);
        cityE = (EditText)findViewById(R.id.cityET);
        zipCodeE = (EditText)findViewById(R.id.zipCodeET);

        save = (Button)findViewById(R.id.saveBTN);
        databseHelper = new DatabseHelper(this);

        Intent intent = getIntent();
         u_name = intent.getStringExtra(CustomersActivity.USERNAME);
        Toast.makeText(this,u_name,Toast.LENGTH_LONG).show();

    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        finish();
        return  true;
    }

   public void saveCustomerInfo(View view){

       String customerName = customerNameE.getText().toString().trim();
       String companyName = companyNameE.getText().toString().trim();
       String phone = phoneE.getText().toString().trim();
       String email = emailE.getText().toString().trim();
       String addressLine1 = addressLine1E.getText().toString().trim();
       String addressLine2 = addressLine2E.getText().toString().trim();
       String city = cityE.getText().toString().trim();
       String zipCode = zipCodeE.getText().toString().trim();

       CustomerInfo customerInfo =  new CustomerInfo(customerName,companyName,phone,email,addressLine1,addressLine2,city,zipCode);

           long id = databseHelper.insertCustomerInfo(customerInfo,u_name);
        //   Toast.makeText(this,Long.toString(id),Toast.LENGTH_LONG).show();

       if(id<0){
           Toast.makeText(this,"UNSUCCESSFUL",Toast.LENGTH_LONG).show();
       }
       else{
           Toast.makeText(this,"SUCCESSFUL",Toast.LENGTH_LONG).show();
           Intent intent = new Intent(CustomersAddActivity.this,CustomersActivity.class);
           intent.putExtra(USERNAME,u_name);
           startActivity(intent);
           finish();
       }



   }


}
