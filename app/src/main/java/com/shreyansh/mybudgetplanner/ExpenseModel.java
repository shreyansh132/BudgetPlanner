package com.shreyansh.mybudgetplanner;

public class ExpenseModel {
    private String title;
    private String description;
    private String date;
    private String time;
    private String paymentMode;
    private String amount;

    public ExpenseModel() {
    }

    public ExpenseModel(String title, String description, String date, String time, String paymentMode, String amount) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.paymentMode = paymentMode;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ExpenseModel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", paymentMode='" + paymentMode + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

}
