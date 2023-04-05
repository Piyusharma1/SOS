package com.example.sos;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText user_name, pass_word;
    FirebaseAuth mAuth;
    Button btn_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        user_name=findViewById(R.id.email);
        pass_word=findViewById(R.id.password);
        Button btn_login = findViewById(R.id.btn_login);
        Button btn_sign = findViewById(R.id.btn_signup);
        mAuth=FirebaseAuth.getInstance();


        final Button btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgotPassword.class);
                startActivity(intent);
            }
        });

        final SharedPreferences SharedPreferences=getSharedPreferences("Data",MODE_PRIVATE);
        final String type=SharedPreferences.getString("Email","");

        if(type.isEmpty()){

            Toast.makeText(getApplicationContext(),
                    "Please login",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent i=new Intent(getApplicationContext(),MapsActivity.class);
            startActivity(i);

        }

        btn_login.setOnClickListener(v -> {
            SharedPreferences.Editor editor=SharedPreferences.edit();
            editor.putString("Email", user_name.getText().toString());
            editor.putString("Password", pass_word.getText().toString());
            editor.commit();


            String email= user_name.getText().toString().trim();
            String password=pass_word.getText().toString().trim();
            if(email.isEmpty())
            {
                user_name.setError("Email is empty");
                user_name.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                user_name.setError("Enter the valid email");
                user_name.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                pass_word.setError("Password is empty");
                pass_word.requestFocus();
                return;
            }
            if(password.length()<6)
            {
                pass_word.setError("Length of password is more than 6");
                pass_word.requestFocus();
                return;
            }
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(LoginActivity.this, MapsActivity.class));
                }
                else
                {
                    Toast.makeText(LoginActivity.this,
                            "Please Check Your login Credentials or Try to Register",
                            Toast.LENGTH_SHORT).show();
                }

            });
        });

        btn_sign.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this,RegisterActivity.class )));

    }

}