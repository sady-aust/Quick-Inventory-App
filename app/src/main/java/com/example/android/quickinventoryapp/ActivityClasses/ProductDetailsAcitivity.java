package com.example.android.quickinventoryapp.ActivityClasses;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.quickinventoryapp.BaseClasses.ProductInfo;
import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;

public class ProductDetailsAcitivity extends AppCompatActivity {
    DatabseHelper databseHelper;
    TextView pNameTV,pIdTV,quantityTV,priceTV;
    Button deleteBtn,updateInfoBtn;
    static String USERNAME = "UserName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_acitivity);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        databseHelper = new DatabseHelper(this);
        pNameTV = (TextView) findViewById(R.id.productNameDetailsTV);
        pIdTV = (TextView)findViewById(R.id.productIDDetailsTV);
        quantityTV = (TextView)findViewById(R.id.quantityDetailsTV);
        priceTV = (TextView)findViewById(R.id.priceDetailsTV);
        deleteBtn = (Button)findViewById(R.id.deleteButton) ;
        updateInfoBtn = (Button)findViewById(R.id.updateInfoBtn);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final String[] productData =  intent.getExtras().getStringArray("productdata");
      // ProductInfo productInfo = databseHelper.getProduct(productData[0],productData[1]);
       // Toast.makeText(this, productInfo.getProductName(), Toast.LENGTH_SHORT).show();
        showProductInfo(productData[0],productData[1],productData[2]);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = databseHelper.deleteAProduct(productData[0],productData[1],productData[2]);
                Intent intent1 = new Intent(ProductDetailsAcitivity.this,ProductsActivity.class);


                intent1.putExtra(USERNAME,productData[2]);
                startActivity(intent1);
            }
        });


        updateInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ProductDetailsAcitivity.this);
                builder.setTitle("Update");

                View viewInflated = LayoutInflater.from(ProductDetailsAcitivity.this).inflate(R.layout.updateinfo, null, false);

                final EditText upPrice = (EditText) viewInflated.findViewById(R.id.updatePriceET);
                final EditText upQuantity = (EditText) viewInflated.findViewById(R.id.updateQuantityET);

                final ProductInfo productInfo = databseHelper.getProduct(productData[0],productData[1],productData[2]);
                upPrice.setText(productInfo.getPrice());
                upQuantity.setText(productInfo.getQuatity());

                builder.setView(viewInflated);


                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newPrice = upPrice.getText().toString();
                        String newQuantity = upQuantity.getText().toString();
                      int v1=  databseHelper.updateProductPrice(productData[0],newPrice,productData[2]);
                     int v2=   databseHelper.addProductQuantity(productData[0],newQuantity,productInfo.getQuatity(),productData[2]);
                        final ProductInfo updateproductInfo = databseHelper.getProduct(productData[0],productData[1],productData[2]);

                        Toast.makeText(ProductDetailsAcitivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        quantityTV.setText(updateproductInfo.getQuatity());
                        priceTV.setText(updateproductInfo.getPrice());


                        dialog.dismiss();
                      // String m_Text = input.getText().toString();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        finish();
        return  true;
    }

    public void showProductInfo(String productName,String productId,String userName){
        ProductInfo productInfo = databseHelper.getProduct(productName,productId,userName);
        Toast.makeText(this, productInfo.getQuatity(), Toast.LENGTH_SHORT).show();
       pNameTV.setText(productInfo.getProductName());
       pIdTV.setText(productInfo.getProductID());
        quantityTV.setText(productInfo.getQuatity()+" "+productInfo.getUnit());
        priceTV.setText(productInfo.getPrice());
    }


}
