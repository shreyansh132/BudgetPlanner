package com.shreyansh.mybudgetplanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddExpenseActivity extends AppCompatActivity {
    private int mYear,mMonth,mDay,mHour,mMinute;
    private EditText titleET,descriptionET,amountET,dateET,timeET,paymentMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        initViewes();
    }

    private void initViewes() {
        dateET = findViewById(R.id.date);
        timeET = findViewById(R.id.time);
        amountET = findViewById(R.id.amount);
        titleET = findViewById(R.id.title);
        descriptionET = findViewById(R.id.description);
        paymentMode = findViewById(R.id.paymentMode);
    }

    public void showDate(View view) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        dateET.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void paymentMode(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddExpenseActivity.this);
        builder.setTitle("Select a Payment Mode");

        final String[] paymentModes = {"PayTm", "PhonePay", "Cash", "Debit Card", "Credit Card"};

        builder.setItems(paymentModes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                paymentMode.setText(paymentModes[which]);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showTime(View view) {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        timeET.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void addExpense(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefrences",MODE_PRIVATE);
        String userName = sharedPreferences.getString("userName",null);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        ExpenseModel expenseModel = new ExpenseModel(
                titleET.getText().toString()
                ,descriptionET.getText().toString()
                ,dateET.getText().toString()
                ,timeET.getText().toString()
                ,paymentMode.getText().toString()
                ,amountET.getText().toString());
        myRef.child(userName).push().setValue(expenseModel);
        Toast.makeText(this, "Expense Added Successfully!!!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AddExpenseActivity.this, MyExpensesActivity.class));
        finish();
    }

}
