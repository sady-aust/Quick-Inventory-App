package com.example.android.quickinventoryapp.ActivityClasses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.quickinventoryapp.BaseClasses.NotificationMessage;
import com.example.android.quickinventoryapp.Custom.NotificationAdapter;
import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    ListView notificationListView;

    private DatabseHelper databseHelper;
    String finalU_name = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        notificationListView = (ListView)findViewById(R.id.notificationLV);

        Intent intent = getIntent();
        String u_name = null;
        try {
            u_name =  intent.getStringExtra(DashBoardActivity.USERNAME).toString();
            //Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
           // Toast.makeText(this, u_name, Toast.LENGTH_SHORT).show();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databseHelper = new DatabseHelper(this);
        final List<NotificationMessage> messageList = databseHelper.getAllNotificationMessage(u_name);

        final List<String> onlyMessage=  getMessage(messageList);
        final List<String> onlyId = getID(messageList);

        final NotificationAdapter adapter = new NotificationAdapter(this,onlyMessage,onlyId);
        notificationListView.setAdapter(adapter);

        finalU_name = u_name;

        notificationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
          //  String text = parent.getItemAtPosition(position).toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(NotificationActivity.this);
                builder.setTitle("Message");
                builder.setMessage("Do You Want TO Delete It?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView textView = (TextView) view.findViewById(R.id.notificationNumberTV);
                        String[] part = textView.getText().toString().split(" ");
                        String notificationId = part[2].toString();
                        // System.out.println("Choosen Country = : " + text);
                        //  Toast.makeText(NotificationActivity.this, notificationId, Toast.LENGTH_SHORT).show();
                        int row = databseHelper.deleteNotification(notificationId,finalU_name);
                        if(row>0){
                            List<NotificationMessage> messageListU = databseHelper.getAllNotificationMessage(finalU_name);
                           NotificationAdapter adapter = new NotificationAdapter(NotificationActivity.this,getMessage(messageListU),getID(messageListU));
                            notificationListView.setAdapter(adapter);
                            Toast.makeText(NotificationActivity.this,"Successfully Deleted",Toast.LENGTH_LONG).show();
                        }

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();





            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.allDeleteItem:
                deleteAll(finalU_name);


        }

        return super.onOptionsItemSelected(item);
    }

    public void deleteAll(final String uName){
        AlertDialog.Builder builder = new AlertDialog.Builder(NotificationActivity.this);
        builder.setTitle("Message");
        builder.setMessage("All Notification will be Deleted?");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              databseHelper.deleteAllNotification(uName);

                List<NotificationMessage> messageListU = databseHelper.getAllNotificationMessage(uName);
                NotificationAdapter adapter = new NotificationAdapter(NotificationActivity.this,getMessage(messageListU),getID(messageListU));
                notificationListView.setAdapter(adapter);
                Toast.makeText(NotificationActivity.this,"Successfully Deleted",Toast.LENGTH_LONG).show();


            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    private List<String> getID(List<NotificationMessage> messageList) {
        List<String> list = new ArrayList<>();
        for(int i=0;i<messageList.size();i++){
            list.add(messageList.get(i).getId().toString());
        }

        return list;
    }

    public List<String> getMessage(List<NotificationMessage> messageList) {

        List<String > list = new ArrayList<>();

        for(int i=0;i<messageList.size();i++){
            list.add(messageList.get(i).getMessgae().toString());
        }

        return  list;
    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onSupportNavigateUp();
        finish();
        return  true;
    }

}
