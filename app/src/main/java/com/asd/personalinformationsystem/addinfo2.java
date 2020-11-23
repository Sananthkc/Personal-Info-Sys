package com.asd.personalinformationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class addinfo2 extends AppCompatActivity {
    EditText title,desc;
    String titlesend,descsend;
    private DatabaseReference mDatabase;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addinfo2);
        title=findViewById(R.id.title);
        desc=findViewById(R.id.desc);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

        public void AddEvent(View view) {
            titlesend=title.getText().toString();
            descsend=desc.getText().toString();

            if(TextUtils.isEmpty(titlesend) || TextUtils.isEmpty(descsend)){
                return;
            }
            Add(titlesend,descsend);

        }

        private void Add(String titlesend, String descsend)
        {

            String id = mDatabase.push().getKey();
            Listdata listdata = new Listdata(id, titlesend, descsend);
            /// new
          /*  userID = fAuth.getCurrentUser().getUid();
            DocumentReference documentReference = fStore.collection("users").document(userID);*/
            ///
            mDatabase.child("Events").child(id).setValue(listdata).
                    addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(addinfo2.this, "Event Added", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), home2.class));
                        }
                    });
        }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), home2.class));
    }
}