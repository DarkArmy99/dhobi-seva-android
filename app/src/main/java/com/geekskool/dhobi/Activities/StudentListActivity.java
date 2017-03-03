package com.geekskool.dhobi.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.geekskool.dhobi.Adapters.StudentAdapter;
import com.geekskool.dhobi.R;

/**
 * Created by manisharana on 27/2/17.
 */

public class StudentListActivity extends AppCompatActivity{

    private CursorAdapter mAdapter;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        mListView = (ListView) findViewById(R.id.rv_courses);

        mAdapter = new StudentAdapter(this, null, 0);
        mListView.setAdapter(mAdapter);

    }

    public void addCourse(View view) {
        Intent intent = new Intent(this, StudentActivity.class);
        startActivity(intent);
    }
}
