package com.example.personalinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPersonActivity extends AppCompatActivity {

    //**********Start of variables***********//
   EditText person_name,person_birth, person_address, person_email,person_mobile, person_qualification, person_school;
   Button add_details;
   DatabaseHelper databaseHelper;
    //**********End of variables************//

    //**************Start of initviews********************//
    public void initviews(){
        person_name = findViewById(R.id.person_name);
        person_birth =findViewById(R.id.person_birth);
        person_address = findViewById(R.id.person_address);
        person_email = findViewById(R.id.person_email);
        person_mobile =findViewById(R.id.person_mobile);
        person_qualification= findViewById(R.id.person_qualification);
        person_school = findViewById(R.id.person_school);

        add_details=findViewById(R.id.add_button);
    }
    //*************End of initviews********************//



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.getWritableDatabase();



        //*******Starting of initviews**********//
        initviews();
        //*******End of initviews************//

        //*************Start of add person*****************//
        add_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (person_name.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext() ,"Fill name field first",Toast.LENGTH_SHORT).show();
                }else if (person_birth.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext() ,"Fill dob field first",Toast.LENGTH_SHORT).show();
                }else if (person_address.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext() ,"Fill address field first",Toast.LENGTH_SHORT).show();
                }else if (person_email.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext() ,"Fill email id first",Toast.LENGTH_SHORT).show();
                }else if (person_mobile.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext() ,"Fill mobile no field first",Toast.LENGTH_SHORT).show();
                }else if (person_qualification.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext() ,"Fill qualification field first",Toast.LENGTH_SHORT).show();
                }else if (person_school.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext() ,"Fill this field first",Toast.LENGTH_SHORT).show();
                }else{
                    //insert datails
                    boolean insert = databaseHelper.insertPerson(person_name.getText().toString(),person_birth.getText().toString(),person_address.getText().toString(),person_email.getText().toString(),person_mobile.getText().toString(),person_qualification.getText().toString(),person_school.getText().toString());

                    if (insert == true){

                        Toast.makeText(getApplicationContext() ,"Details Inserted Successfully",Toast.LENGTH_SHORT).show();
                    }else if (insert==false){

                        Toast.makeText(getApplicationContext() ,"Data Inserted Failed",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        //************End of add person*****************//

    }
}