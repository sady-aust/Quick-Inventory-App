package com.example.android.quickinventoryapp.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.android.quickinventoryapp.R;

public class SpleshScreenActivity extends AppCompatActivity {
    private static int SPLESH_SCREEN_TIME_OUT =5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh_screen);
        getSupportActionBar().hide();
       // Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SpleshScreenActivity.this,SignInActivity.class);

                startActivity(intent);

                finish();

            }

        },SPLESH_SCREEN_TIME_OUT);




    }
}
