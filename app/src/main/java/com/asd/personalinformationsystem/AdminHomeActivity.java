package com.asd.personalinformationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminHomeActivity extends AppCompatActivity {
    DatabaseReference mref;
    ConstraintLayout lay;
    private TextView txtFullName, txtEmail, txtPhone, txtOccupation;
    private Button  btn_search,btnLogout;
    private EditText editTxtName;
    private ImageView imgProfile;
    DataSnapshot dataSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        lay = findViewById(R.id.parent);
        txtFullName = findViewById(R.id.txt_full_name);
        txtEmail = findViewById(R.id.txt_email_address);
        txtPhone = findViewById(R.id.txt_phone);
        txtOccupation = findViewById(R.id.txt_occupation);
        btnLogout = findViewById(R.id.btn_logout);
        btn_search = findViewById(R.id.btn_search);
        editTxtName = findViewById(R.id.edit_txt_name);
        imgProfile = findViewById(R.id.img_profile);

        final String mName = editTxtName.getText().toString();

       btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mName.equals("8888888888")){
                    FirebaseFirestore db=
                }

            }
        });

    }
}