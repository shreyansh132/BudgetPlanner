package com.shreyansh.mybudgetplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {
    private EditText userName, name, password,email;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initViews();
    }
    private void initViews() {
        database = FirebaseDatabase.getInstance();
        userName = findViewById(R.id.userName);
        name = findViewById(R.id.fName);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
    }

    public void Register(View view) {
        database.getReference("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(userName.getText().toString()).exists()){
                    Toast.makeText(RegistrationActivity.this, "User already exists!!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseReference myRef = database.getReference().
                            child("users").child(userName.getText().toString());
                    myRef.child("name").setValue(name.getText().toString());
                    myRef.child("password").setValue(password.getText().toString());
                    myRef.child("email").setValue(email.getText().toString());
                    myRef.child("userName").setValue(userName.getText().toString());

                    SharedPreferences.Editor editor = getSharedPreferences("myPrefrences", MODE_PRIVATE).edit();
                    editor.putBoolean("isLogin", true);
                    editor.putString("userName", userName.getText().toString());
                    editor.apply();
                    Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(RegistrationActivity.this, MyExpensesActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void signIn(View view) {
        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
    }

}
