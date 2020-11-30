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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private EditText userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }
    private void initViews() {
        database = FirebaseDatabase.getInstance();
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
    }
    public void login(View view) {
        if(userName.getText().toString().isEmpty())
            Toast.makeText(this, "User Name can't be Empty", Toast.LENGTH_SHORT).show();

        else if(password.getText().toString().isEmpty())
            Toast.makeText(this, "Password can't be Empty", Toast.LENGTH_SHORT).show();

        else {
            database.getReference("users").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(userName.getText().toString()).exists()) {
                        if (dataSnapshot.child(userName.getText().toString()).child("password").getValue().equals(password.getText().toString())) {

                            SharedPreferences.Editor editor = getSharedPreferences("myPrefrences", MODE_PRIVATE).edit();
                            editor.putBoolean("isLogin", true);
                            editor.putString("userName", userName.getText().toString());
                            editor.apply();

                            Toast.makeText(LoginActivity.this, "Login Successful!!!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MyExpensesActivity.class));
                            finish();
                        } else
                            Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(LoginActivity.this, "User doesn't Exists", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void signUp(View view) {
        startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
    }
}
