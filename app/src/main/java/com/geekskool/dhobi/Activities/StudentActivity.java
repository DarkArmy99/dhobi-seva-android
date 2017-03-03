package com.geekskool.dhobi.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.geekskool.dhobi.Helpers.Constants;
import com.geekskool.dhobi.R;

/**
 * Created by manisharana on 16/2/17.
 */
public class StudentActivity extends AppCompatActivity {

    private EditText mNameView;
    private EditText mSeatNumberView;
    private EditText mRoomNumberView;
    private String courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        Intent intent = getIntent();
        courseId = intent.getStringExtra(Constants.COURSE_ID);
        mNameView = (EditText) findViewById(R.id.et_student_name);
        mRoomNumberView = (EditText) findViewById(R.id.et_student_room_number);
        mSeatNumberView = (EditText) findViewById(R.id.et_student_seat_number);
    }

    public void saveStudent(View view) {
        String name = mNameView.getText().toString();
        String seatNUmber = mSeatNumberView.getText().toString();
        String roomNumber = mRoomNumberView.getText().toString();
        finish();
    }

}
