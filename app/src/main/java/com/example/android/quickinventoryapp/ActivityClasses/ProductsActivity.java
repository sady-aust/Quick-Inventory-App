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
import com.example.android.quickinventoryapp.Custom.MyAdapter;
import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {
    FloatingActionButton fab;
    ListView productsLV;
    DatabseHelper databseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.plus);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = (FloatingActionButton) findViewById(R.id.fabcustomer);
        productsLV = (ListView)findViewById(R.id.productsLV);

        databseHelper = new DatabseHelper(this);
        final  List<ProductInfo> products = databseHelper.getAllProductInfo();
        MyAdapter adapter = new MyAdapter(this,getProductsName(products),getProductsId(products));
        productsLV.setAdapter(adapter);
        productsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ProductsActivity.this,ProductDetailsAcitivity.class);
                ProductInfo info = products.get(position);
                String[] data = {info.getProductName(),info.getProductID()};
               // Toast.makeText(ProductsActivity.this, data[0]+" "+data[1], Toast.LENGTH_SHORT).show();
                intent.putExtra("productdata",data);
                startActivity(intent);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsActivity.this,ProductsAddActitivty.class);
                startActivity(intent);
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
