package com.geekskool.manisha.vipsana.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.geekskool.manisha.vipsana.Contracts.Contract;
import com.geekskool.manisha.vipsana.R;

public class MainActivity extends AppCompatActivity {

    private EditText mDesc;
    private EditText mLocation;
    private EditText mDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDesc = (EditText) findViewById(R.id.et_desc);
        mDuration = (EditText) findViewById(R.id.et_duration);
        mLocation = (EditText) findViewById(R.id.et_location);

    }

    public void saveCourse(View view) {
        String desc = mDesc.getText().toString();
        int duration = Integer.valueOf(mDuration.getText().toString());
        String location = mLocation.getText().toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.CourseEntry.COLUMN_NAME,desc);
        contentValues.put(Contract.CourseEntry.COLUMN_DURATION,duration);
        contentValues.put(Contract.CourseEntry.COLUMN_LOCATION,location);

        getContentResolver().insert(Contract.CourseEntry.CONTENT_URI, contentValues);

        Intent intent = new Intent(this, StudentActivity.class);
        startActivity(intent);
    }
}
