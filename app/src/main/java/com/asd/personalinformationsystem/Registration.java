package com.asd.personalinformationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Registration extends AppCompatActivity {

    EditText userName, userPassword, userEmail, userPhone, userOccupation;
    Button regButton;
    TextView userLogin;
    public static final String TAG = "TAG";
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseDatabase rootNode;
    DatabaseReference mDatabase;
    String userID;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);




        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
                //Upload the data to the database
                rootNode = FirebaseDatabase.getInstance();
                mDatabase = rootNode.getReference("Users");
                String id=mDatabase.push().getKey();

                String name = userName.getText().toString();
                String email = userEmail.getText().toString();
                String phone = userPhone.getText().toString();
                String occ = userOccupation.getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name, id, email, phone, occ);
                mDatabase.child(phone).setValue(helperClass);

            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, MainActivity.class));
            }
        });
    }

    private void setupUIViews() {
        userName = (EditText) findViewById(R.id.eTPersonName);
        userEmail = (EditText) findViewById(R.id.eTREmailAddress);
        userPassword = (EditText) findViewById(R.id.eTRPassword);
        userPhone = (EditText) findViewById(R.id.eTRPhone);
        regButton = (Button) findViewById(R.id.buttonRegister);
        userLogin = (TextView) findViewById(R.id.textView5);
        userOccupation = findViewById(R.id.eTROccupation);
    }

    private void validate() {
        Boolean result = false;

        final String mName = userName.getText().toString();
        String mPassword = userPassword.getText().toString();
        final String mEmail = userEmail.getText().toString();
        final String mPhone = userPhone.getText().toString();
        final String mOccupation = userOccupation.getText().toString();
        if (TextUtils.isEmpty(mName)) {

        }
        if (TextUtils.isEmpty(mEmail)) {
            userEmail.setError("Email is Required.");
            return;
        }

        if (TextUtils.isEmpty(mPassword)) {
            userPassword.setError("Password is Required.");
            return;
        }

        if (mPassword.length() < 6) {
            userPassword.setError("Password Must be >= 6 Characters");
            return;
        }
        if (mPhone.length() < 10 && mPhone.length() > 11) {
            userPhone.setError("Enter a valid 10 digit number");
        }
        progressBar.setVisibility(View.VISIBLE);

        //register the user to firebase

        fAuth.createUserWithEmailAndPassword(mEmail, mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    // send verification link

                    FirebaseUser fuser = fAuth.getCurrentUser();
                    fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Registration.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG,"onFailure: Email not sent " + e.getMessage());
                        }
                    });

                    Toast.makeText(Registration.this, "User Created.", Toast.LENGTH_SHORT).show();
                    userID = fAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fStore.collection("users").document(userID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("fName", mName);
                    user.put("email", mEmail);
                    user.put("phone", mPhone);
                    user.put("occupation",mOccupation);
                    user.put("id", userID);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "onSuccess: user Profile is created for " + userID);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: " + e.toString());
                        }
                    });



                } else {
                    Toast.makeText(Registration.this, "Error ! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Registration.this,MainActivity.class);
        startActivity(i);
    }
}
