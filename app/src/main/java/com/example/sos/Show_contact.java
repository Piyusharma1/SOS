package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;
import java.util.Map;


public class Show_contact extends AppCompatActivity {

    ListView listView;
    DbHelper db;
    List<ContactModel> list;
    CustomAdapter customAdapter;
    Button bth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcontact);


        listView = (ListView) findViewById(R.id.ListView);
        db = new DbHelper(this);
        list = db.getAllContacts();
        customAdapter = new CustomAdapter(this, list);
        listView.setAdapter(customAdapter);

        final Button bth = (Button) findViewById(R.id.bth);
        bth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Show_contact.this,MapsActivity.class);
                startActivity(intent);
            }
        });



    }
}