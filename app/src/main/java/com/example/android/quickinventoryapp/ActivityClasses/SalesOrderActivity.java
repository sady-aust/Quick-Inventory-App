package com.example.android.quickinventoryapp.ActivityClasses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.quickinventoryapp.BaseClasses.ProductInfo;
import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesOrderActivity extends AppCompatActivity {

    Spinner item ;
    EditText dateET,quantityET;
    DatabseHelper databaseHelper;
    Button donebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seles_order);
        item = (Spinner)findViewById(R.id.item);
        dateET = (EditText)findViewById(R.id.dateET) ;
        quantityET =(EditText)findViewById(R.id.quantityETSO);
        donebtn =(Button)findViewById(R.id.doneBtn);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        dateET.setText(date);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        databaseHelper = new DatabseHelper(this);

     final List<ProductInfo> list = databaseHelper.getAllProductInfo();
        final List<String> products = new ArrayList<>();

        for(int i=0;i<list.size();i++){
            products.add(list.get(i).getProductName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SalesOrderActivity.this,R.layout.spinner_item,products);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        item.setAdapter(adapter);

       donebtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String name = products.get(item.getSelectedItemPosition());
               String userQuantity = quantityET.getText().toString();
              // Toast.makeText(SalesOrderActivity.this, userQuantity, Toast.LENGTH_SHORT).show();
               for(int i=0;i<list.size();i++){
                   if(list.get(i).getProductName().equals(name)){
                       if(Integer.parseInt(userQuantity)<=Integer.parseInt(list.get(i).getQuatity())) {
                           databaseHelper.updateProductQuantity(name, userQuantity, list.get(i).getQuatity());
                          long id= databaseHelper.insertHistory("You Sale "+userQuantity+" pices "+name+" On "+date.toString());
                           if(id<0) {
                               Toast.makeText(SalesOrderActivity.this, "unsuccessful", Toast.LENGTH_SHORT).show();
                           }
                           else{
                               Toast.makeText(SalesOrderActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                           }
                       }
                       else{
                           Toast.makeText(SalesOrderActivity.this, "You can't sale this quantity", Toast.LENGTH_SHORT).show();
                       }



                   }
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
