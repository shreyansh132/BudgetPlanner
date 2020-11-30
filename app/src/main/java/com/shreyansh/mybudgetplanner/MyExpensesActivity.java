package com.shreyansh.mybudgetplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyExpensesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ExpenseModel> expenseModelList;
    private SharedPreferences sharedPreferences;
    private FirebaseDatabase database;
    DatabaseReference myRef;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_expenses);
        initViewes();
        setUpDataBaseAndSharedPrefrences();
    }

    private void initViewes() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        sharedPreferences = getSharedPreferences("myPrefrences",MODE_PRIVATE);
        userName = sharedPreferences.getString("userName",null);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }

    private void setUpDataBaseAndSharedPrefrences() {
        myRef.child(userName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                expenseModelList = new ArrayList<>();
                for(DataSnapshot d : dataSnapshot.getChildren()){
                    ExpenseModel expenseModel = d.getValue(ExpenseModel.class);
                    expenseModelList.add(expenseModel);
                }
                MyExpenseAdapter myExpenseAdapter = new MyExpenseAdapter(MyExpensesActivity.this,expenseModelList);
                recyclerView.setClipToPadding(false);
                recyclerView.setClipToPadding(false);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MyExpensesActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(myExpenseAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void addExpense(View view) {
        startActivity(new Intent(MyExpensesActivity.this, AddExpenseActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
