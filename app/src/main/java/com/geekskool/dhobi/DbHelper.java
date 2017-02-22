package com.geekskool.dhobi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.geekskool.dhobi.Contracts.Contract;

/**
 * Created by manisharana on 17/2/17.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "vipasanaDatabase.db";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        addCourseTable(db);
        addStudentTable(db);
        addPurchaseTable(db);
    }

    private void addPurchaseTable(SQLiteDatabase db) {
        db.execSQL(Contract.CourseEntry.getCreateStatement());
    }

    private void addStudentTable(SQLiteDatabase db) {
        db.execSQL(Contract.StudentEntry.getCreateStatement());
    }

    private void addCourseTable(SQLiteDatabase db) {
        db.execSQL(Contract.PurchaseEntry.getCreateStatement());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
