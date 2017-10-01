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


    int personID;
   String userName,userpassword,shopName,shopOwnerName;
    static String USER_NAME ="UserName";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
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
        UserInfo user =databseHelper.receivingDatafromUserInfoTable(givenUserName,givenPassword);
        userName = user.getUserName();
        userpassword = user.getPassword();

        if(isUsernameAndPasswordMatched(givenUserName,givenPassword)){
           Intent intent = new Intent(SignInActivity.this,DashBoardActivity.class);
            intent.putExtra(USER_NAME,userName);



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




}
