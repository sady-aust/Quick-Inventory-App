package com.example.android.quickinventoryapp.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.android.quickinventoryapp.BaseClasses.ProductInfo;
import com.example.android.quickinventoryapp.Custom.CustomerAdapter;
import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {
    FloatingActionButton fab;
    ListView productsLV;
    DatabseHelper databseHelper;

    static String USERNAME = "UserName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.plus);

        Intent intent = getIntent();
        String u_name = null;
        try {
            u_name =  intent.getStringExtra(DashBoardActivity.USERNAME).toString();
         //   Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
           // Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = (FloatingActionButton) findViewById(R.id.fabcustomer);
        productsLV = (ListView)findViewById(R.id.productsLV);

        databseHelper = new DatabseHelper(this);
        final  List<ProductInfo> products = databseHelper.getAllProductInfo(u_name);
        CustomerAdapter adapter = new CustomerAdapter(this,getProductsName(products),getProductsId(products));
        productsLV.setAdapter(adapter);
        final String finalU_name = u_name;
        productsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProductsActivity.this,ProductDetailsAcitivity.class);
                ProductInfo info = products.get(position);
                String[] data = {info.getProductName(),info.getProductID(), finalU_name};
               // Toast.makeText(ProductsActivity.this, data[0]+" "+data[1], Toast.LENGTH_SHORT).show();
                intent.putExtra("productdata",data);
                startActivity(intent);

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsActivity.this,ProductsAddActitivty.class);


                intent.putExtra(USERNAME,finalU_name);

                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        finish();
        return  true;
    }

    public List<String> getProductsName(List<ProductInfo> list){
        List<String> productsName = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            productsName.add(list.get(i).getProductName());
        }

        return productsName;
    }
    List<String> getProductsId(List<ProductInfo> list){
        List<String> productIds = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            productIds.add(list.get(i).getProductID());
        }

        return productIds;


    }

}
