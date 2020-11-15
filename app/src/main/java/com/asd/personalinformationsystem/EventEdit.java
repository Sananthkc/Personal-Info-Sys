package com.asd.personalinformationsystem;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EventEdit extends AppCompatActivity {
    EditText title, desc;
    String titlesend, descsend;
    private DatabaseReference mDatabase;
    private Listdata listdata;
    Button updates, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        updates = findViewById(R.id.updatesbutton);
        delete = findViewById(R.id.deletedbutton);


        final Intent i = getIntent();
        String gettitle = i.getStringExtra("title");
        String getdesc = i.getStringExtra("desc");
        final String id = i.getStringExtra("id");


        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        title.setText(gettitle);
        desc.setText(getdesc);

        updates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
                UpdateEvents(id);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
                deleteEvents(id);
            }
        });
    }

    private void UpdateEvents(String id) {
        titlesend = title.getText().toString();
        descsend = desc.getText().toString();
        Listdata listdata = new Listdata(id, titlesend, descsend);
        mDatabase.child("Events").child(id).setValue(listdata).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(EventEdit.this, "Events Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), home2.class));
                    }
                });
    }

    private void deleteEvents(String id) {
        mDatabase.child("Events").child(id).removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EventEdit.this, "Events Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), home2.class));

                    }
                });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), home2.class));
    }
}
