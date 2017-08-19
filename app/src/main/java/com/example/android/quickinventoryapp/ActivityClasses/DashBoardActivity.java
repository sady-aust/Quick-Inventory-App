package com.example.android.quickinventoryapp.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.android.quickinventoryapp.R;

public class DashBoardActivity extends AppCompatActivity {
    ImageView customers,products,salesOrder,history,signoutIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        customers = (ImageView)findViewById(R.id.coutomarsId);
        products = (ImageView)findViewById(R.id.productsId);
        salesOrder = (ImageView)findViewById(R.id.salesOrderIV);
        history = (ImageView)findViewById(R.id.hsitoryIV) ;
        signoutIV =(ImageView)findViewById(R.id.signOutIV);

        customers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this,CustomersActivity.class);

                startActivity(intent);
            }
        });

        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DashBoardActivity.this,ProductsActivity.class);
                startActivity(intent);
            }
        });

        salesOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this,SalesOrderActivity.class);
                startActivity(intent);
            }
        });
        signoutIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });
    }


}
