package com.geekskool.dhobi.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.geekskool.dhobi.Adapters.CourseAdapter;
import com.geekskool.dhobi.Adapters.RecyclerItemClickListener;
import com.geekskool.dhobi.Db.CourseRepository;
import com.geekskool.dhobi.Helpers.Constants;
import com.geekskool.dhobi.Models.Course;
import com.geekskool.dhobi.R;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements RecyclerItemClickListener.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private CourseRepository courseRepository;
    private RealmResults<Course> courseList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        courseRepository = new CourseRepository();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        courseList = courseRepository.getAll();
        RealmRecyclerViewAdapter mAdapter = new CourseAdapter(courseList);
        mRecyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration decoration = new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(decoration);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,mRecyclerView,this));
    }

    public void addModelView(View view) {
        Intent intent = new Intent(this, AddCourseActivity.class);
        startActivity(intent);
    }

    public void goToNextActivity(Course course) {
        Intent intent = new Intent(this, StudentListActivity.class);
        intent.putExtra(Constants.COURSE_ID, course.getId());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecyclerView.setAdapter(null);
        courseRepository.close();
    }

    @Override
    public void onItemClick(View view, int position) {
        int itemPosition = mRecyclerView.getChildLayoutPosition(view);
        goToNextActivity(courseList.get(itemPosition));
    }
}
