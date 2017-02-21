package com.geekskool.dhobi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.geekskool.dhobi.Contracts.Contract;

/**
 * Created by manisharana on 17/2/17.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
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
        db.execSQL(
                "CREATE TABLE " + Contract.PurchaseEntry.TABLE_NAME + " (" +
                        Contract.PurchaseEntry._ID + " INTEGER PRIMARY KEY, " +
                        Contract.PurchaseEntry.COLUMN_NAME + " TEXT UNIQUE NOT NULL, "+
                        Contract.PurchaseEntry.COLUMN_QUANTITY + " INTEGER, "+
                        Contract.PurchaseEntry.COLUMN_RATE + " INTEGER, "+
                        Contract.PurchaseEntry.COLUMN_DATE + " TEXT NOT NULL, "+
                        Contract.PurchaseEntry.COLUMN_TOTAL + " INTEGER);");
    }

    private void addStudentTable(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + Contract.StudentEntry.TABLE_NAME + " (" +
                Contract.StudentEntry._ID + " INTEGER PRIMARY KEY, " +
                Contract.StudentEntry.COLUMN_NAME + " TEXT UNIQUE NOT NULL, " +
                Contract.StudentEntry.COLUMN_AGE + " INTEGER, " +
                Contract.StudentEntry.COLUMN_GENDER + " TEXT, " +
                Contract.StudentEntry.COLUMN_ROOM_NUMBER + " TEXT, " +
                Contract.StudentEntry.COLUMN_DEPOSIT + " INTEGER);";
        db.execSQL(sql);
    }

    private void addCourseTable(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + Contract.CourseEntry.TABLE_NAME + " (" +
                        Contract.CourseEntry._ID + " INTEGER PRIMARY KEY, " +
                        Contract.CourseEntry.COLUMN_NAME + " TEXT UNIQUE NOT NULL, "+
                        Contract.CourseEntry.COLUMN_DURATION + " INTEGER NOT NULL, "+
                        Contract.CourseEntry.COLUMN_LOCATION + " TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
