package com.geekskool.dhobi.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.geekskool.dhobi.Db.CourseRepository;
import com.geekskool.dhobi.Helpers.Constants;
import com.geekskool.dhobi.Models.Course;
import com.geekskool.dhobi.R;

import java.util.Calendar;

import io.realm.Realm;

import static com.geekskool.dhobi.Helpers.Util.isInvalid;

public class AddCourseActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener, Realm.Transaction.OnSuccess {

    private EditText mName;
    private EditText mLocation;
    private EditText mDuration;
    private long mStartDate;
    private DatePicker mDatePicker;
    private CourseRepository courseRepo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        courseRepo = new CourseRepository();

        mName = (EditText) findViewById(R.id.et_course_name);
        mDuration = (EditText) findViewById(R.id.et_course_duration);
        mLocation = (EditText) findViewById(R.id.et_course_location);
        mDatePicker = (DatePicker) findViewById(R.id.dp_date);

        setUpDatePicker();
    }

    private void setUpDatePicker() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        mDatePicker.init(year, month, day, this);
        mDatePicker.setMinDate(cal.getTimeInMillis() - Constants.MIN_IN_MILLIS);
    }

    public void closeDialogBox(View view) {
        finish();
    }


    public void validateCourse(View view) {
        Course course = getCourse();
        if (course != null) {
            courseRepo.add(course,this);
        }
    }

    @Nullable
    private Course getCourse() {
        String name = mName.getText().toString();
        String duration = mDuration.getText().toString();
        String location = mLocation.getText().toString();

        if (isInvalid(name)) {setError(mName);return null;}
        if (isInvalid(duration)) {setError(mDuration);return null;}
        if (isInvalid(location)) {setError(mLocation);return null;}

        Integer days = Integer.valueOf(duration);
        return new Course(days, location, mStartDate, getEndDate(days), name);
    }

    private void setError(EditText editText) {
        editText.setError(getString(R.string.invalid_error));
    }

    private long getEndDate(Integer days) {
        return mStartDate + Constants.getMilliseconds(days);
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        mStartDate = cal.getTimeInMillis();
    }

    @Override
    public void onSuccess() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        courseRepo.close();
    }

}
