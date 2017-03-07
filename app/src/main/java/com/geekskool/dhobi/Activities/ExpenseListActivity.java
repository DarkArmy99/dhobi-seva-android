package com.geekskool.dhobi.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.geekskool.dhobi.Adapters.ExpenseAdapter;
import com.geekskool.dhobi.Db.ExpenseRepository;
import com.geekskool.dhobi.Helpers.Constants;
import com.geekskool.dhobi.Models.Expense;
import com.geekskool.dhobi.R;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;

import static com.geekskool.dhobi.Helpers.Util.isInvalid;

/**
 * Created by manisharana on 3/3/17.
 */

public class ExpenseListActivity extends AppCompatActivity implements Realm.Transaction.OnSuccess {

    private RecyclerView mRecyclerView;
    private ExpenseRepository expenseRepo;
    private String studentId;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        ((FloatingActionButton) findViewById(R.id.fab_add)).setImageResource(R.drawable.ic_expense);

        expenseRepo = new ExpenseRepository();
        studentId = getStudentId();
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        RealmRecyclerViewAdapter mAdapter = new ExpenseAdapter(expenseRepo.getAllExpenses(studentId));
        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration decoration = new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(decoration);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private String getStudentId() {
        Intent intent = getIntent();
        return intent != null ? intent.getStringExtra(Constants.STUDENT_ID) : Constants.EMPTY_STRING;
    }

    public void addModelView(View view) {
        Intent intent = new Intent(this, AddExpenseActivity.class);
        intent.putExtra(Constants.STUDENT_ID, studentId);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecyclerView.setAdapter(null);
        expenseRepo.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_expense_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_deposit:
                openAddDepositDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openAddDepositDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_deposit_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        setPostiveButton(builder, view);
        setNegativeButton(builder);

        alertDialog = builder.create();
        alertDialog.show();
    }

    private void setNegativeButton(AlertDialog.Builder builder) {
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogBox, int id) {
                dialogBox.cancel();
            }
        });
    }

    private void setPostiveButton(AlertDialog.Builder builder, View view) {
        final EditText depositView = (EditText) view.findViewById(R.id.et_deposit_amount);
        builder.setCancelable(false)
                .setPositiveButton(R.string.saveExpense, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        validateDeposit(depositView);
                    }
                });
    }

    private void validateDeposit(EditText depositView) {
        Expense expense = getExpense(depositView);
        if (expense != null)
            expenseRepo.addExpense(studentId, expense, this);
    }

    private Expense getExpense(EditText depositView) {
        String amount = depositView.getText().toString();

        if (isInvalid(amount)) {
            setError(depositView);
            return null;
        }

        return new Expense(Constants.DEPOSIT, Float.valueOf(amount), Calendar.getInstance().getTimeInMillis());
    }

    private void setError(EditText editText) {
        editText.setError(getString(R.string.invalid_error));
    }

    @Override
    public void onSuccess() {
        alertDialog.dismiss();
    }
}
