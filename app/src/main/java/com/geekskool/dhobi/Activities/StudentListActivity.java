package com.geekskool.dhobi.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.geekskool.dhobi.Adapters.StudentAdapter;
import com.geekskool.dhobi.Db.StudentRepository;
import com.geekskool.dhobi.Helpers.Constants;
import com.geekskool.dhobi.Models.Student;
import com.geekskool.dhobi.R;

import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by manisharana on 27/2/17.
 */

public class StudentListActivity extends AppCompatActivity{

    private String courseId;
    private RecyclerView mRecyclerView;
    private StudentRepository studentRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        studentRepo = new StudentRepository();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        courseId = getCourseId();
        setUpRecyclerView(courseId);
    }

    private String getCourseId() {
        Intent intent = getIntent();
        return intent != null ? courseId = intent.getStringExtra(Constants.COURSE_ID) : "";
    }

    private void setUpRecyclerView(String courseId) {
        RealmRecyclerViewAdapter mAdapter = new StudentAdapter(this, studentRepo.getAllStudents(courseId));
        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration decoration = new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(decoration);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void addModelView(View view) {
        Intent intent = new Intent(this, AddStudentActivity.class);
        intent.putExtra(Constants.COURSE_ID, courseId);
        startActivity(intent);
    }

    public void goToNextActivity(Student student) {
        Intent intent = new Intent(this, PurchaseActivity.class);
        intent.putExtra(Constants.COURSE_ID, courseId);
        intent.putExtra(Constants.STUDENT_ID, student.getId());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecyclerView.setAdapter(null);
        studentRepo.close();
    }
}
