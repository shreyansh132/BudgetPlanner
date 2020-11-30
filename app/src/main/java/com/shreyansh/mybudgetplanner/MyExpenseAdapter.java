package com.shreyansh.mybudgetplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyExpenseAdapter extends RecyclerView.Adapter<MyExpenseAdapter.MyExpenseViewHolder>{
    private Context context;
    private List<ExpenseModel> expenseList;

    public MyExpenseAdapter(Context context, List<ExpenseModel> expenseModelList) {
        this.context = context;
        this.expenseList = expenseModelList;
    }

    @NonNull
    @Override
    public MyExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_expense_layout, null,false);
        return new MyExpenseAdapter.MyExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyExpenseViewHolder holder, int position) {
        holder.title.setText(expenseList.get(position).getTitle());
        holder.description.setText(expenseList.get(position).getDescription());
        holder.amount.setText("₹ "+expenseList.get(position).getAmount());
        String dateTime = expenseList.get(position).getDate()+"&"+expenseList.get(position).getTime();
        holder.dateTime.setText(dateTime);
        holder.paymentMode.setText(expenseList.get(position).getPaymentMode());
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class MyExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, dateTime, amount, paymentMode;

        public MyExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            amount = itemView.findViewById(R.id.amount);
            paymentMode = itemView.findViewById(R.id.paymentMode);
            dateTime = itemView.findViewById(R.id.date_time);
            //time = itemView.findViewById(R.id.time);
        }
    }
}
