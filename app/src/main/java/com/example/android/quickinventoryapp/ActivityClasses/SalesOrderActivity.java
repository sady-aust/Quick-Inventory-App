package com.example.android.quickinventoryapp.ActivityClasses;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.quickinventoryapp.BaseClasses.CustomerInfo;
import com.example.android.quickinventoryapp.BaseClasses.HistoryInfo;
import com.example.android.quickinventoryapp.BaseClasses.NotificationMessage;
import com.example.android.quickinventoryapp.BaseClasses.ProductInfo;
import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class SalesOrderActivity extends AppCompatActivity {

    Spinner item;
    EditText dateET, quantityET, productPriceET;
    DatabseHelper databaseHelper;
    Button donebtn;
    AutoCompleteTextView customerNameACTV;
    String totalPrice ="";

    static String USERNAME = "UserName";

    NotificationCompat.Builder notification;
    private static final int unique_Id = 45612;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seles_order);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        item = (Spinner) findViewById(R.id.itemSpnner);
        dateET = (EditText) findViewById(R.id.dateET);
        quantityET = (EditText) findViewById(R.id.quantityET);
        donebtn = (Button) findViewById(R.id.doneBtn);
        customerNameACTV = (AutoCompleteTextView) findViewById(R.id.customerNameACTV);
        productPriceET = (EditText) findViewById(R.id.priceET);

        Intent intent = getIntent();
        String u_name = null;
        try {
            u_name =  intent.getStringExtra(DashBoardActivity.USERNAME).toString();
           // Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
          //  Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }

        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        dateET.setText(date);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        databaseHelper = new DatabseHelper(this);


        final List<ProductInfo> allProductInfo = databaseHelper.getAllProductInfo(u_name);
        final List<String> products = getProductsName(allProductInfo);


        final List<CustomerInfo> allCustomerInfo = databaseHelper.getAllCustomerInfo(u_name);
        final List<String> customerNames = getCustomersName(allCustomerInfo);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SalesOrderActivity.this, R.layout.spinner_item, products);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        item.setAdapter(adapter);


        ArrayAdapter<String> customerNameAdapter = new ArrayAdapter<String>(SalesOrderActivity.this, android.R.layout.select_dialog_item, customerNames);
        customerNameACTV.setAdapter(customerNameAdapter);

        quantityET.setText("0");

        item.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //productPriceET.setText(getProductPrice(allProductInfo,products.get(item.getSelectedItemPosition())));
                String seletedItem = products.get(item.getSelectedItemPosition());
                if(quantityET.getText().toString()!=null){
                float t = getProductPrice(allProductInfo, seletedItem.trim());

               // Toast.makeText(SalesOrderActivity.this, Float.toString( getProductPrice(allProductInfo, seletedItem.trim())), Toast.LENGTH_LONG).show();
                     totalPrice = Float.toString(t* parseInt(quantityET.getText().toString()));
                    productPriceET.setText(totalPrice);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        final String finalU_name = u_name;
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = products.get(item.getSelectedItemPosition());
                String userQuantity = quantityET.getText().toString();
                String customerName = customerNameACTV.getText().toString().trim();

                for (int i = 0; i < allProductInfo.size(); i++) {

                    if (allProductInfo.get(i).getProductName().equals(productName)) {
                        if (parseInt(userQuantity) <= parseInt(allProductInfo.get(i).getQuatity())) {
                           int availableQuantity = databaseHelper.updateProductQuantity(productName, userQuantity, allProductInfo.get(i).getQuatity(), finalU_name);
                            if(availableQuantity<Integer.parseInt(allProductInfo.get(i).getAlertMeWhen())){
                                getQuanityNotification(allProductInfo.get(i).getProductName(),availableQuantity,finalU_name);
                            }
                            long id = databaseHelper.insertHistory(new HistoryInfo(customerName, productName, date.toString(), userQuantity,totalPrice),finalU_name);

                            if (id < 0) {
                                Toast.makeText(SalesOrderActivity.this, "unsuccessful", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SalesOrderActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SalesOrderActivity.this, "You can't sale this quantity", Toast.LENGTH_SHORT).show();
                        }


                    }
                }

            }
        });

    }

    private List<String> getProductsName(List<ProductInfo> allProductInfo) {
        List<String> products = new ArrayList();
        for (int i = 0; i < allProductInfo.size(); i++) {
            products.add(allProductInfo.get(i).getProductName());
        }
        return products;
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        finish();
        return true;
    }

    public List<String> getCustomersName(List<CustomerInfo> customers) {
        List<String> customersName = new ArrayList<>();
        for (int i = 0; i < customers.size(); i++) {
            customersName.add(customers.get(i).getCutomerName());
        }

        return customersName;
    }

    public float getProductPrice(List<ProductInfo> allProductInfos, String myProductName) {
        for (int i = 0; i < allProductInfos.size(); i++) {
            if (allProductInfos.get(i).getProductName().equals(myProductName)) {
                return Float.parseFloat(allProductInfos.get(i).getPrice());
            }
        }
        return 0;
    }

    private void getQuanityNotification(String productName,int quantity,String uName) {
        //Biuilding the notification
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker(productName+"'s Quantity is in shortage");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Alart!");
        String message = "Currently "+productName+"'s Quanity is "+quantity+". Please add more "+productName+" in your Stock.";
        notification.setContentText(message);

        databaseHelper.insertNotificationMessage(new NotificationMessage(message),uName);


        try {
            Intent intent = new Intent(this, NotificationActivity.class);
            intent.putExtra(USERNAME, uName);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(pendingIntent);
        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        notification.setLights(Color.BLUE, 500, 500);
        notification.setStyle(new NotificationCompat.InboxStyle());
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notification.setSound(sound);

        long[] pattern = {500,500,500,500,500,500,500,500,500};
        notification.setVibrate(pattern);



        //Build The Notification and issue It

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(unique_Id,notification.build());
    }


}
