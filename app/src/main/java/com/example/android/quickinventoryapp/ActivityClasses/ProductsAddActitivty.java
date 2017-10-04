package com.example.android.quickinventoryapp.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.quickinventoryapp.BaseClasses.ProductInfo;
import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;

public class ProductsAddActitivty extends AppCompatActivity {
    EditText productNameET,productIdET,quantityET,priceET,alertET,unitET;
    DatabseHelper databseHelper;
    String u_name ="";
    static String USERNAME = "UserName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_add_actitivty);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        productNameET = (EditText)findViewById(R.id.productNameET);
        productIdET =(EditText)findViewById(R.id.productIdET);
        quantityET =(EditText)findViewById(R.id.quantityET);
        unitET =(EditText)findViewById(R.id.unitET);
        priceET =(EditText)findViewById(R.id.priceET);
        alertET =(EditText)findViewById(R.id.alertET);
        databseHelper = new DatabseHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
         u_name = null;
        try {
            u_name =  intent.getStringExtra(ProductsActivity.USERNAME).toString();
            Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }


    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        finish();
        return  true;
    }

    public void saveproduct(View view){
        String productName = productNameET.getText().toString().trim();
        String productId = productIdET.getText().toString().trim();
        String quantity  = quantityET.getText().toString().trim();
        String unit = unitET.getText().toString().trim();
        String price = priceET.getText().toString().trim();
        String alert = alertET.getText().toString().trim();

        ProductInfo productInfo = new ProductInfo(productName,productId,quantity,unit,price,alert);
       long id= databseHelper.insertProduct(productInfo,u_name);
        if(id<0){
            Toast.makeText(this,"UNSUCCESSFUL",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Added",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ProductsAddActitivty.this,ProductsActivity.class);


            intent.putExtra(USERNAME,u_name);
            startActivity(intent);
            finish();
        }


    }

}
