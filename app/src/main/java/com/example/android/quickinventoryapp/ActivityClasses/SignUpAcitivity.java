package com.example.android.quickinventoryapp.ActivityClasses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.quickinventoryapp.BaseClasses.UserInfo;
import com.example.android.quickinventoryapp.DatabaseWorks.DatabseHelper;
import com.example.android.quickinventoryapp.R;

public class SignUpAcitivity extends AppCompatActivity {
    private EditText userNameET,passWordET,shopNameET,shopOwnerNameET;
    private Button signUPBtn;
    static String USERNAME = "UserName";

    DatabseHelper databseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_acitivity);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        userNameET = (EditText) findViewById(R.id.signUpUserNameET);
        passWordET = (EditText)findViewById(R.id.signUpPasswordET);
        shopNameET = (EditText)findViewById(R.id.signUpShopNameET);
        shopOwnerNameET =(EditText)findViewById(R.id.shopOwnerNameET);
        signUPBtn = (Button)findViewById(R.id.signUpBtn);

        databseHelper = new DatabseHelper(this);
    }

    public void addUser(View view) {
        final String userName = userNameET.getText().toString().trim();
        String passWord = passWordET.getText().toString().trim();
        String shopName = shopNameET.getText().toString().trim();
        String shopOwnerName = shopOwnerNameET.getText().toString().trim();

        final UserInfo userInfo = new UserInfo(userName,passWord,shopName,shopOwnerName);
     long id =   databseHelper.insertUserinfo(userInfo);
        if(id<0){
            Toast.makeText(this,"UNSUCCESSFUL",Toast.LENGTH_LONG).show();
        }
        else{
          //  Toast.makeText(this,"SUCCESSFUL",Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Message");
            builder.setMessage("Thank You for Sign Up.Do you want to Sign in now?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(SignUpAcitivity.this,DashBoardActivity.class);
                    String[] data = { userInfo.getUserName(),userInfo.getShopOwneName()};
                    intent.putExtra(USERNAME,data);
                    startActivity(intent);
                    finish();
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

    }






  /*  public String getData(String name){

        SQLiteDatabase db = databseHelper.getWritableDatabase();
        String[] columns = {DatabseHelper.NAME, DatabseHelper.PASSWORD};

        Cursor cursor = db.query(DatabseHelper.TABLE_NAME,columns, DatabseHelper.NAME+" = '"+ name+"'",null,null,null,null);

        StringBuffer buffer = new StringBuffer();

        while(cursor.moveToNext()){
            int index1 =  cursor.getColumnIndex(DatabseHelper.NAME);
            int index2 =  cursor.getColumnIndex(DatabseHelper.PASSWORD);

            String userN = cursor.getString(index1);
            String pass = cursor.getString(index2);
            buffer.append(userN+"\n");

        }

        return buffer.toString();
    }
*/



}
