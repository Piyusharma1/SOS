package com.example.sos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashActivity extends AppCompatActivity {


    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Intent i = new Intent(SplashActivity.this, MapsActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    //No User is Logged in
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }
                /////////////////////


            }
        }, 1000);
    }
}