package com.example.android.quickinventoryapp.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.quickinventoryapp.R;

public class DashBoardActivity extends AppCompatActivity {
    ImageView customers,products,salesOrder,history,signoutIV,analysisIV;
    static String USERNAME = "UserName";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_dash_board);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        customers = (ImageView)findViewById(R.id.coutomarsId);
        products = (ImageView)findViewById(R.id.productsId);
        salesOrder = (ImageView)findViewById(R.id.salesOrderIV);
        history = (ImageView)findViewById(R.id.hsitoryIV) ;
        signoutIV =(ImageView)findViewById(R.id.signOutIV);
        analysisIV = (ImageView)findViewById(R.id.analysisIV);




        Intent intent = getIntent();
        String u_name = null;
        try {
             u_name =  intent.getStringExtra(USERNAME).toString();
            Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }


        final String finalU_name = u_name;
        customers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this,CustomersActivity.class);
                intent.putExtra(USERNAME, finalU_name);

                startActivity(intent);
            }
        });

        products.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DashBoardActivity.this,ProductsActivity.class);
                intent.putExtra(USERNAME, finalU_name);

                startActivity(intent);
            }
        });



        salesOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this,SalesOrderActivity.class);
                intent.putExtra(USERNAME, finalU_name);

                startActivity(intent);
            }
        });
        signoutIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this,SignInActivity.class);
                intent.putExtra(USERNAME, finalU_name);

                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this,HistoryActivity.class);
                intent.putExtra(USERNAME, finalU_name);

                startActivity(intent);
            }
        });

        analysisIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashBoardActivity.this,AnalysisActivity.class);
                intent.putExtra(USERNAME, finalU_name);

                startActivity(intent);
            }
        });


    }


}
