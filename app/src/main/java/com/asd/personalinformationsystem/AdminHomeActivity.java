package com.asd.personalinformationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AdminHomeActivity extends AppCompatActivity {

    private TextView txtFullName, txtEmail, txtPhone, txtOccupation;
    private Button btnSearch, btnLogout;
    private EditText editTxtName;
    private ImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        txtFullName = findViewById(R.id.txt_full_name);
        txtEmail = findViewById(R.id.txt_email_address);
        txtPhone = findViewById(R.id.txt_phone);
        txtOccupation = findViewById(R.id.txt_occupation);
        btnSearch = findViewById(R.id.btn_search);
        btnLogout = findViewById(R.id.btn_logout);
        editTxtName = findViewById(R.id.edit_txt_name);
        imgProfile = findViewById(R.id.img_profile);
    }
}