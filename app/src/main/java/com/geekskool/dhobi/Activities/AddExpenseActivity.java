package com.geekskool.dhobi.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.geekskool.dhobi.Db.ExpenseRepository;
import com.geekskool.dhobi.Helpers.Constants;
import com.geekskool.dhobi.Models.Expense;
import com.geekskool.dhobi.R;

import java.util.Calendar;

import io.realm.Realm;

import static com.geekskool.dhobi.Helpers.Util.isInvalid;

/**
 * Created by manisharana on 16/2/17.
 */
public class AddExpenseActivity extends AppCompatActivity implements Realm.Transaction.OnSuccess, DatePicker.OnDateChangedListener {


    private EditText mNameView;
    private EditText mQuantityView;
    private EditText mRateView;
    private DatePicker mDateView;
    private String studentId;
    private ExpenseRepository expenseRepo;
    private long mExpenseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        expenseRepo = new ExpenseRepository();
        mNameView = (EditText) findViewById(R.id.et_expense_name);
        mQuantityView = (EditText) findViewById(R.id.et_expense_quantity);
        mRateView = (EditText) findViewById(R.id.et_expense_rate);
        mDateView = (DatePicker) findViewById(R.id.dp_date);

        studentId = getStudentId();
        setUpDatePicker();
    }

    private void setUpDatePicker() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        mDateView.init(year, month, day, this);
        mExpenseDate = cal.getTimeInMillis();
    }

    private String getStudentId() {
        Intent intent = getIntent();
        return intent != null ? intent.getStringExtra(Constants.STUDENT_ID):Constants.EMPTY_STRING;
    }

    public void closeDialogBox(View view) {
        finish();
    }

    // TODO: name,dropdown fix
    public void validateExpense(View view) {
        Expense expense = getExpense();
        if(expense != null)
            expenseRepo.addExpense(studentId,expense,this);
    }

    @Nullable
    private Expense getExpense() {
        String name = mNameView.getText().toString();
        String quantityStr = mQuantityView.getText().toString(); ;
        String rateStr = mRateView.getText().toString();

        if (isInvalid(name)) {setError(mNameView);return null;}
        if (isInvalid(quantityStr)) {setError(mQuantityView);return null;}
        if (isInvalid(rateStr)) {setError(mRateView);return null;}

        int quantity = Integer.valueOf(quantityStr);
        float rate = Float.valueOf(rateStr);
        float amount = rate * quantity;
        return new Expense(name,quantity,rate,amount,mExpenseDate);
    }

    private void setError(EditText editText) {
        editText.setError(getString(R.string.invalid_error));
    }

    @Override
    public void onSuccess() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        expenseRepo.close();
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        mExpenseDate = cal.getTimeInMillis();
    }
}
