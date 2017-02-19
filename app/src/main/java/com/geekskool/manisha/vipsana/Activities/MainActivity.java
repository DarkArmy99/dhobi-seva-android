package com.geekskool.manisha.vipsana.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.geekskool.manisha.vipsana.Adapters.CourseAdapter;
import com.geekskool.manisha.vipsana.Models.Course;
import com.geekskool.manisha.vipsana.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mDesc;
    private EditText mLocation;
    private EditText mDuration;
    private RecyclerView mRecyclerView;
    private CourseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.list_view);

        List<Course> courseList = new ArrayList<>();
        mAdapter = new CourseAdapter(courseList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

//        mDesc = (EditText) findViewById(R.id.et_course_desc);
//        mDuration = (EditText) findViewById(R.id.et_course_duration);
//        mLocation = (EditText) findViewById(R.id.et_course_location);

    }

    public void saveCourse(View view) {
//        String desc = mDesc.getText().toString();
//        int duration = Integer.valueOf(mDuration.getText().toString());
//        String location = mLocation.getText().toString();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(Contract.CourseEntry.COLUMN_NAME,desc);
//        contentValues.put(Contract.CourseEntry.COLUMN_DURATION,duration);
//        contentValues.put(Contract.CourseEntry.COLUMN_LOCATION,location);
//
//        getContentResolver().insert(Contract.CourseEntry.CONTENT_URI, contentValues);
//
//        Intent intent = new Intent(this, StudentActivity.class);
//        startActivity(intent);
    }
}
