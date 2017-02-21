package com.geekskool.dhobi.Activities;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.geekskool.dhobi.Contracts.Contract;
import com.geekskool.dhobi.R;

import java.util.Calendar;

/**
 * Created by manisharana on 20/2/17.
 */
public class AddCourseActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener {

    private EditText mDesc;
    private EditText mLocation;
    private EditText mDuration;
    private final String DATE_PICKER_TAG = "datePicker";
    private DatePicker mStartDate;
    private long currentDate;
    private long endDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        mDesc = (EditText) findViewById(R.id.et_course_desc);
        mDuration = (EditText) findViewById(R.id.et_course_duration);
        mLocation = (EditText) findViewById(R.id.et_course_location);
        mStartDate = (DatePicker) findViewById(R.id.dp_start_date);
        mStartDate.init(year,month,day,this);
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
        contentValues.put(Contract.CourseEntry.COLUMN_START_DATE,currentDate);
        contentValues.put(Contract.CourseEntry.COLUMN_END_DATE,endDate);
        contentValues.put(Contract.CourseEntry.COLUMN_DURATION,duration);
        contentValues.put(Contract.CourseEntry.COLUMN_LOCATION,location);

        getContentResolver().insert(Contract.CourseEntry.CONTENT_URI, contentValues);
        finish();
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.DAY_OF_MONTH,day);
        currentDate = cal.getTimeInMillis();
        int duration = Integer.valueOf(mDuration.getText().toString());
        cal.add(Calendar.DAY_OF_MONTH,duration);
        endDate = cal.getTimeInMillis();
    }
}
