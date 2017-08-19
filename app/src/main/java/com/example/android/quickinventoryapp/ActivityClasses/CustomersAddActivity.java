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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databseHelper = new DatabseHelper(this);
        setContentView(R.layout.activity_cusromars_add);
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
       long id = databseHelper.insertCustomerInfo(customerInfo);
       if(id<0){
           Toast.makeText(this,"UNSUCCESSFUL",Toast.LENGTH_LONG).show();;
       }
       else{
           Toast.makeText(this,"SUCCESSFUL",Toast.LENGTH_LONG).show();;
           Intent intent = new Intent(CustomersAddActivity.this,CustomersActivity.class);
           startActivity(intent);
       }




   }


}
