package com.geekskool.dhobi.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.geekskool.dhobi.Adapters.ExpenseAdapter;
import com.geekskool.dhobi.Db.StudentRepository;
import com.geekskool.dhobi.Helpers.Constants;
import com.geekskool.dhobi.R;

import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by manisharana on 3/3/17.
 */

public class ExpenseListActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private StudentRepository studentRepo;
    private String studentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        studentRepo = new StudentRepository();

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        studentId = getStudentId();
        setUpRecyclerView(studentId);
    }

    private void setUpRecyclerView(String studentId) {
        RealmRecyclerViewAdapter mAdapter = new ExpenseAdapter(studentRepo.getAllExpenses(studentId));
        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration decoration = new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(decoration);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private String getStudentId() {
        Intent intent = getIntent();
        return intent != null ? intent.getStringExtra(Constants.STUDENT_ID) :"";
    }

    public void addModelView(View view) {
        Intent intent = new Intent(this, AddExpenseActivity.class);
        intent.putExtra(Constants.STUDENT_ID,studentId);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecyclerView.setAdapter(null);
        studentRepo.close();
    }


}
