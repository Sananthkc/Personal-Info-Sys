package com.asd.personalinformationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdminHomeActivity extends AppCompatActivity {

    ConstraintLayout lay;
    private TextView txtFullName, txtEmail, txtPhone, txtOccupation;
    private Button  btn_search,btnLogout, btnDelete, btnView;
    private EditText editTxtPhone;
    private NoteViewModel viewModel;
    private NoteRepository repository;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        btnLogout = findViewById(R.id.btn_logout);
        editTxtPhone = findViewById(R.id.edit_txt_phone);
        btn_search = findViewById(R.id.btn_search);
        recyclerView = findViewById(R.id.recycler_view);
        btnDelete = findViewById(R.id.btn_del);
        btnView = findViewById(R.id.btn_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AdminHomeActivity.this, MainActivity.class));
                finish();
            }
        });

        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NoteViewModel.class);
        repository = new NoteRepository(getApplication());

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneNumber = editTxtPhone.getText().toString();
                //TODO: After getting the phone number from the ADMIN search for the user
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        final List<User> users = repository.getAllUsers(phoneNumber);
                        if(users.size()==0) {
                            AdminHomeActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setVisibility(View.GONE);
                                    Toast.makeText(AdminHomeActivity.this, "No data found!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            AdminHomeActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setVisibility(View.VISIBLE);
                                    adapter.setNotes(users);
                                }
                            });
                        }
                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String phoneNumber = editTxtPhone.getText().toString();
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        final List<User> users = repository.getAllUsers(phoneNumber);
                        if(users.size()==0) {
                            AdminHomeActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setVisibility(View.GONE);
                                    Toast.makeText(AdminHomeActivity.this, "No user found!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            viewModel.delete(users.get(0));
                            AdminHomeActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    recyclerView.setVisibility(View.GONE);
                                    Toast.makeText(AdminHomeActivity.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getUsers().observe(AdminHomeActivity.this, new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        if(users.size()==0) {
                            recyclerView.setVisibility(View.GONE);
                            Toast.makeText(AdminHomeActivity.this, "No users available", Toast.LENGTH_SHORT).show();
                        } else {
                            recyclerView.setVisibility(View.VISIBLE);
                            adapter.setNotes(users);
                        }
                    }
                });
            }
        });
    }

}