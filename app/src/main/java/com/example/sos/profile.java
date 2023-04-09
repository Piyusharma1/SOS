package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile extends AppCompatActivity {
    TextView txtInfo;
    Button btnLogOut;
    private EditText user_name, pass_word;
    FirebaseAuth mAuth;
    Button btn_reset,logout;
    SharedPreferences shp;
    SharedPreferences.Editor shpEditor;

    FirebaseUser currentUser ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.Profile);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch(item.getItemId())
            {
                case R.id.dashboard:
                    startActivity(new Intent(getApplicationContext(),dashboard.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.about:
                    startActivity(new Intent(getApplicationContext(),about1.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.Profile:

                    return true;
            }
            return false;
        });


        ////////////////////////////////////////




        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        userData();
    }

    public void userData() {
        TextView userEmail = findViewById(R.id.userEmail);
        userEmail.setText(currentUser.getEmail());
        //////////////////////////////////////////

        final Button btn_reset = (Button) findViewById(R.id.btn_reset);


        btn_reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(profile.this,ForgotPassword.class);
                startActivity(intent);
            }
        });


        btnLogOut = findViewById(R.id.btnLogOut);

        shp = getSharedPreferences("Data", MODE_PRIVATE);


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
    }




    public void Logout() {
        try {
            if (shp == null)
                shp = getSharedPreferences("data", MODE_PRIVATE);

            shpEditor = shp.edit();
            shpEditor.putString("email", "");
            shpEditor.clear();
            shpEditor.remove("data");
            shpEditor.commit();

            Intent i = new Intent(profile.this, LoginActivity.class);
            startActivity(i);
            finish();

        } catch (Exception ex) {
            Toast.makeText(profile.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }
}