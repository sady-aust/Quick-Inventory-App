package com.example.android.quickinventoryapp.ActivityClasses;

/*
   Author
    Md. Toufiqul Islam
    Rahat Mushfiq Abir
    Ahsanullah University Of Science & Technology

 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.quickinventoryapp.BaseClasses.UserInfo;
import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;

public class SignInActivity extends AppCompatActivity {
    TextView dontHaveAccountTV;
    Button signInBtn;
    EditText userNameEditText, passwordEditText;
    DatabseHelper databseHelper;
    NotificationCompat.Builder notification;
    private static final int unique_Id = 45612;
    private  UserInfo user;


    int personID;
   String userName,userpassword,shopName,shopOwnerName;
    static String USER_NAME ="UserName";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        //for notification
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
       // getWelcomeNotification();

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        dontHaveAccountTV = (TextView) findViewById(R.id.donthvTV);
        signInBtn = (Button) findViewById(R.id.signInBtn);
        userNameEditText = (EditText) findViewById(R.id.userNameET);
        passwordEditText = (EditText) findViewById(R.id.passwordET);
        databseHelper = new DatabseHelper(this);


        dontHaveAccountTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpAcitivity.class);

                startActivity(intent);

            }
        });


    }

    public void signIn(View view) {

         String givenUserName = userNameEditText.getText().toString().trim();
         String givenPassword = passwordEditText.getText().toString().trim();
        try {
             user = databseHelper.receivingDatafromUserInfoTable(givenUserName, givenPassword);
        }catch(Exception e){
            Toast.makeText(this, "Please Sign Up", Toast.LENGTH_LONG).show();
        }
        userName = user.getUserName();
        userpassword = user.getPassword();

        if(isUsernameAndPasswordMatched(givenUserName,givenPassword)){
           Intent intent = new Intent(SignInActivity.this,DashBoardActivity.class);

          String [] data = {user.getUserName().toString(),user.getShopOwneName().toString()};

            intent.putExtra(USER_NAME,data);

           // getWelcomeNotification();

            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "UserName and Password Doesn't Match", Toast.LENGTH_LONG).show();
        }


    }



    public boolean isUsernameAndPasswordMatched(String name,String passwrod){

        if(name.equals(userName) && passwrod.equals(userpassword)){
            return true;
        }
        else{
            return false;
        }
    }
   /* private void getWelcomeNotification() {
        //Biuilding the notification
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("This Is Ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Here is the title");
        notification.setContentText("I am the body text of yor notification");

        Intent intent = new Intent(this,DashBoardActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //Build The Notification and issue It

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(unique_Id,notification.build());
    }
*/




}
