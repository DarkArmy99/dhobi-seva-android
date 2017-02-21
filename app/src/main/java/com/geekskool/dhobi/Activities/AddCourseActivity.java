package com.geekskool.dhobi.Activities;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.geekskool.dhobi.Contracts.Contract;
import com.geekskool.dhobi.R;

/**
 * Created by manisharana on 20/2/17.
 */
public class AddCourseActivity extends AppCompatActivity {

    private EditText mDesc;
    private EditText mLocation;
    private EditText mDuration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_course);
        setFinishOnTouchOutside(false);
        mDesc = (EditText) findViewById(R.id.et_course_desc);
        mDuration = (EditText) findViewById(R.id.et_course_duration);
        mLocation = (EditText) findViewById(R.id.et_course_location);
    }

    public void closeDialogBox(View view) {
        finish();
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
        finish();
    }

}
