package com.geekskool.dhobi.Activities;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;

import com.geekskool.dhobi.Adapters.CourseAdapter;
import com.geekskool.dhobi.Contracts.Contract;
import com.geekskool.dhobi.R;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private CursorAdapter mAdapter;
    private ListView mListView;
    private static final int LOADER_ID = 10;
    public static final int COL_COURSE_ID = 0;
    public static final int COL_COURSE_NAME = 1;
    public static final int COL_COURSE_START_DATE = 2;
    public static final int COL_COURSE_END_DATE = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        mListView = (ListView) findViewById(R.id.list_view);

        mAdapter = new CourseAdapter(this, null, 0);
        mListView.setAdapter(mAdapter);
        getLoaderManager().initLoader(LOADER_ID, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id != LOADER_ID) {
            return null;
        }
        // add sort order based on start date
        String[] projection = {
                Contract.CourseEntry.TABLE_NAME + "." + Contract.CourseEntry._ID,
                Contract.CourseEntry.COLUMN_NAME,
                Contract.CourseEntry.COLUMN_START_DATE,
                Contract.CourseEntry.COLUMN_END_DATE};

        return new CursorLoader(this, Contract.CourseEntry.CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    public void addCourse(View view) {
        Intent intent = new Intent(this, AddCourseActivity.class);
        startActivity(intent);
    }

}
