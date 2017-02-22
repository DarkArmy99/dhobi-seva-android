package com.geekskool.dhobi.Activities;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.geekskool.dhobi.Contracts.Contract.CourseEntry;
import com.geekskool.dhobi.Models.Course;
import com.geekskool.dhobi.R;

import java.util.Calendar;

import static com.geekskool.dhobi.Contracts.Contract.CourseEntry.COLUMN_DURATION;
import static com.geekskool.dhobi.Contracts.Contract.CourseEntry.COLUMN_END_DATE;
import static com.geekskool.dhobi.Contracts.Contract.CourseEntry.COLUMN_LOCATION;
import static com.geekskool.dhobi.Contracts.Contract.CourseEntry.COLUMN_NAME;
import static com.geekskool.dhobi.Contracts.Contract.CourseEntry.COLUMN_START_DATE;

/**
 * Created by manisharana on 20/2/17.
 */
public class AddCourseActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener {

    private EditText mName;
    private EditText mLocation;
    private EditText mDuration;
    private DatePicker mStartDate;
    private long startDate;
    private long endDate;
    private View rootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        rootView = findViewById(android.R.id.content);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        mName = (EditText) findViewById(R.id.et_course_name);
        mDuration = (EditText) findViewById(R.id.et_course_duration);
        mLocation = (EditText) findViewById(R.id.et_course_location);
        mStartDate = (DatePicker) findViewById(R.id.dp_start_date);
        mStartDate.setMinDate(cal.getTimeInMillis());
        mStartDate.init(year,month,day,this);
    }

    public void closeDialogBox(View view) {
        finish();
    }


    public void validateCourse(View view) {
        Course course = getCourse();
        if(course == null)
            Snackbar.make(rootView,R.string.should_not_be_empty,Snackbar.LENGTH_LONG).show();
        else {
            saveCourse(course);
            finish();
        }

    }

    private Course getCourse() {
        String name = mName.getText().toString();
        if(isInvalid(name)) return null;
        String duration = mDuration.getText().toString();
        if(isInvalid(duration)) return null;
        String location = mLocation.getText().toString();
        if(isInvalid(location)) return null;

        return new Course(Integer.valueOf(duration), location, startDate, endDate, name);
    }

    private boolean isInvalid(String name) {
        return name == null || name.trim().length() == 0;
    }

    private void saveCourse(Course course) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,course.getDesc());
        values.put(COLUMN_START_DATE, course.getStartDate());
        values.put(COLUMN_END_DATE,course.getEndDate());
        values.put(COLUMN_DURATION,course.getDuration());
        values.put(COLUMN_LOCATION,course.getLocation());
        getContentResolver().insert(CourseEntry.CONTENT_URI, values);
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.DAY_OF_MONTH,day);
        startDate = cal.getTimeInMillis();
        int duration = Integer.valueOf(mDuration.getText().toString());
        cal.add(Calendar.DAY_OF_MONTH,duration);
        endDate = cal.getTimeInMillis();
    }
    
}
