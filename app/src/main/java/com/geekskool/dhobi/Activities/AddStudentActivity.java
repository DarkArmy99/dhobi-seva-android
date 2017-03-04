package com.geekskool.dhobi.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.geekskool.dhobi.Db.StudentRepository;
import com.geekskool.dhobi.Helpers.Constants;
import com.geekskool.dhobi.Models.Student;
import com.geekskool.dhobi.R;

import io.realm.Realm;

import static com.geekskool.dhobi.Helpers.Util.isInvalid;

/**
 * Created by manisharana on 16/2/17.
 */
public class AddStudentActivity extends AppCompatActivity implements Realm.Transaction.OnSuccess {

    private EditText mNameView;
    private EditText mSeatNumberView;
    private EditText mRoomNumberView;
    private String courseId;
    private StudentRepository studentRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        studentRepo = new StudentRepository();

        getCourseId();
        mNameView = (EditText) findViewById(R.id.et_student_name);
        mRoomNumberView = (EditText) findViewById(R.id.et_student_room_number);
        mSeatNumberView = (EditText) findViewById(R.id.et_student_seat_number);
    }

    private void getCourseId() {
        Intent intent = getIntent();
        if(intent !=null)
            courseId = intent.getStringExtra(Constants.COURSE_ID);
    }

    public void validateStudent(View view) {
        Student student = getStudent();
        if (student != null)
            studentRepo.addStudent(courseId,student,this);
    }

    public void closeDialogBox(View view) {
        finish();
    }

    @Nullable
    private Student getStudent() {
        String name = mNameView.getText().toString();
        String seatNumber = mSeatNumberView.getText().toString();
        String roomNumber = mRoomNumberView.getText().toString();

        if (isInvalid(name)) {setError(mNameView);return null;}
        if (isInvalid(seatNumber)) {setError(mSeatNumberView);return null;}
        if (isInvalid(roomNumber)) {setError(mRoomNumberView);return null;}

        return new Student(name,roomNumber,seatNumber);
    }

    private void setError(EditText editText) {
        editText.setError(getString(R.string.invalid_error));
    }

    @Override
    public void onSuccess() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        studentRepo.close();
    }


}
