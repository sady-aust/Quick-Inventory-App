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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.quickinventoryapp.BaseClasses.NotificationMessage;
import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;

public class DashBoardActivity extends AppCompatActivity {
    ImageView customers,products,salesOrder,history,signoutIV,analysisIV,aboutUs,noticiationIV;
    static String USERNAME = "UserName";


    private DatabseHelper databseHelper;

    NotificationCompat.Builder notification;
    private static final int unique_Id = 45612;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_dash_board);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        //for notification


        databseHelper = new DatabseHelper(this);


        customers = (ImageView)findViewById(R.id.coutomarsId);
        products = (ImageView)findViewById(R.id.productsId);
        salesOrder = (ImageView)findViewById(R.id.salesOrderIV);
        history = (ImageView)findViewById(R.id.hsitoryIV) ;
        signoutIV =(ImageView)findViewById(R.id.signOutIV);
        analysisIV = (ImageView)findViewById(R.id.analysisIV);
        aboutUs = (ImageView)findViewById(R.id.aboutUsIV);
        noticiationIV = (ImageView)findViewById(R.id.notificationIV);






        final Intent intent = getIntent();

        String u_name = null;
        String shopOwnerName =null;

        try {
             String[] data =  intent.getStringArrayExtra(USERNAME);
            u_name = data[0].toString();
            shopOwnerName = data[1].toString();

          //  Toast.makeText(this, shopOwnerName, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
           // Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
        getWelcomeNotification(shopOwnerName,u_name);


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
                finish();
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

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent1 = new Intent(DashBoardActivity.this,AboutUsActivity.class);
                startActivity(intent1);
            }
        });

        noticiationIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DashBoardActivity.this,NotificationActivity.class);
                intent1.putExtra(USERNAME,finalU_name);
                startActivity(intent1);
            }
        });


    }

    private void getWelcomeNotification(String ownerName,String uName) {
        //Biuilding the notification
        notification.setSmallIcon(R.mipmap.ic_launcher);

        notification.setTicker("Welcome "+ownerName);
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Welcome");
        String message = "Hey "+ownerName+" ! Welcome to Quick Inventory App";
        notification.setContentText(message);

        databseHelper.insertNotificationMessage(new NotificationMessage(message),uName);
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
