package com.example.android.quickinventoryapp.ActivityClasses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.quickinventoryapp.BaseClasses.HistoryInfo;
import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;

import java.util.List;

public class HistoryDetailsActivity extends AppCompatActivity {
    TextView customerName,productName,quantity,total,date,sellId;
    DatabseHelper databseHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        sellId =(TextView)findViewById(R.id.sellidTV);
        customerName = (TextView)findViewById(R.id.customernamedetailsTV);
        productName = (TextView)findViewById(R.id.productIDDetailsTV);
        quantity = (TextView)findViewById(R.id.quantityDetailsTV);
        total =(TextView)findViewById(R.id.totalPriceDetailsTV);
        date = (TextView)findViewById(R.id.dateDetailsTV);
        databseHelper = new DatabseHelper(this);
        List<HistoryInfo> historyInfos = databseHelper.getAllHsitory(SignInActivity.USER_NAME);






    }

    public void setData(String sell_Id,String customer_Name,String product_Name,String productQuantity,String sellDate,String totalPrice){
        customerName.setText(customer_Name);
        productName.setText(product_Name);
        quantity.setText(productQuantity);
        sellId.setText(sell_Id);
        date.setText(sellDate);
        total.setText(totalPrice);
    }

    public int calculatePrice(String quantity,String price){
        return Integer.parseInt(quantity)*Integer.parseInt(price);
    }
}
