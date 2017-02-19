package com.geekskool.manisha.vipsana.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.geekskool.manisha.vipsana.Contracts.Contract;
import com.geekskool.manisha.vipsana.R;

/**
 * Created by manisharana on 16/2/17.
 */
public class StudentActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mGender;
    private EditText mAge;
    private EditText mDeposit;
    private EditText mRoomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        mName = (EditText) findViewById(R.id.et_student_name);
        mGender = (EditText) findViewById(R.id.et_student_gender);
        mAge = (EditText) findViewById(R.id.et_student_age);
        mDeposit = (EditText) findViewById(R.id.et_student_deposit);
        mRoomNumber = (EditText) findViewById(R.id.et_student_room_number);

    }

    public void saveStudent(View view) {
        String name = mName.getText().toString();
        String gender = mGender.getText().toString();
        String roomNumber = mRoomNumber.getText().toString();

        int age = Integer.valueOf(mAge.getText().toString());
        int deposit = Integer.valueOf(mDeposit.getText().toString());

        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.StudentEntry.COLUMN_NAME,name);
        contentValues.put(Contract.StudentEntry.COLUMN_GENDER,gender);
        contentValues.put(Contract.StudentEntry.COLUMN_AGE,age);
        contentValues.put(Contract.StudentEntry.COLUMN_ROOM_NUMBER,roomNumber);
        contentValues.put(Contract.StudentEntry.COLUMN_DEPOSIT,deposit);
        getContentResolver().insert(Contract.StudentEntry.CONTENT_URI, contentValues);

        Intent intent = new Intent(this, PurchaseActivity.class);
        startActivity(intent);
    }

}
