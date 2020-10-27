package com.example.personalinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    //**********Start of variables***********//
    Button exitApp , Add_person , view_persons;
    DatabaseHelper databaseHelper;

    //**********End of variables************//

    //**************Start of initviews********************//
    public void initviews(){
        exitApp = findViewById(R.id.exitApp);
        Add_person = findViewById(R.id.add_personButton);
        view_persons = findViewById(R.id.view_All_personButton);
    }
    //*************End of initviews********************//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        databaseHelper =new DatabaseHelper(this);
        databaseHelper.getWritableDatabase();

        //*******Starting of initviews**********//
        initviews();
        //*******End of initviews************//

        //*********Start of Exit button********//
        exitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //********End of Exit button*********//

       //***********Start of Add person button**************//
       Add_person.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(HomeActivity.this, AddPersonActivity.class));
           }
       });
       //*********End of Add person button***************//

    }
}