package com.geekskool.dhobi.Models;

/**
 * Created by manisharana on 4/3/17.
 */

public class Result {

    private String studentName;
    private String expense;
    private String laundry;
    private String deposit;
    private String balance;

    public Result(String studentName, String expense, String laundry, String deposit, String balance) {
        this.studentName = studentName;
        this.expense = expense;
        this.laundry = laundry;
        this.deposit = deposit;
        this.balance = balance;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getDeposit() {
        return deposit;
    }

    public String getLaundry() {
        return laundry;
    }

    public String getBalance() {
        return balance;
    }
}
