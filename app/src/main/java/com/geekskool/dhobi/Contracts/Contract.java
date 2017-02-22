package com.geekskool.dhobi.Contracts;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by manisharana on 17/2/17.
 */
public class Contract {

    public static final String CONTENT_AUTHORITY = "com.geekskool.manisha.vipasna";
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_COURSE = "courses";
    public static final String PATH_STUDENT = "students";
    public static final String PATH_PURCHASE = "purchases";

    public static final class CourseEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_COURSE).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_URI + "/" + PATH_COURSE;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_COURSE;

        public static final String TABLE_NAME = "courses";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_START_DATE = "start_date";
        public static final String COLUMN_END_DATE = "end_date";
        public static final String COLUMN_DURATION = "duration";
        public static final String COLUMN_LOCATION = "location";

        public static Uri buildCourseUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getCreateStatement() {
            return "CREATE TABLE " + Contract.CourseEntry.TABLE_NAME + " (" +
                    Contract.CourseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Contract.CourseEntry.COLUMN_NAME + " TEXT NOT NULL, "+
                    Contract.CourseEntry.COLUMN_START_DATE + " INTEGER NOT NULL, "+
                    Contract.CourseEntry.COLUMN_END_DATE + " INTEGER NOT NULL, "+
                    Contract.CourseEntry.COLUMN_DURATION + " INTEGER NOT NULL, "+
                    Contract.CourseEntry.COLUMN_LOCATION + " TEXT NOT NULL);";
        }
    }


    public static final class StudentEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_STUDENT).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_URI + "/" + PATH_STUDENT;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_STUDENT;

        public static final String TABLE_NAME = "students";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_GENDER = "gender";
        public static final String COLUMN_ROOM_NUMBER = "room_number";
        public static final String COLUMN_DEPOSIT = "deposit";

        public static Uri buildCourseUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getCreateStatement() {
            return "CREATE TABLE " + Contract.StudentEntry.TABLE_NAME + " (" +
                    Contract.StudentEntry._ID + " INTEGER PRIMARY KEY, " +
                    Contract.StudentEntry.COLUMN_NAME + " TEXT UNIQUE NOT NULL, " +
                    Contract.StudentEntry.COLUMN_AGE + " INTEGER, " +
                    Contract.StudentEntry.COLUMN_GENDER + " TEXT, " +
                    Contract.StudentEntry.COLUMN_ROOM_NUMBER + " TEXT, " +
                    Contract.StudentEntry.COLUMN_DEPOSIT + " INTEGER);";
        }
    }

    public static final class PurchaseEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PURCHASE).build();
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/" + CONTENT_URI + "/" + PATH_PURCHASE;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_PURCHASE;
        public static final String TABLE_NAME = "purchases";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_RATE = "rate";
        public static final String COLUMN_TOTAL = "total";
        public static final String COLUMN_DATE= "date";

        public static Uri buildCourseUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static String getCreateStatement() {
            return "CREATE TABLE " + Contract.PurchaseEntry.TABLE_NAME + " (" +
                    Contract.PurchaseEntry._ID + " INTEGER PRIMARY KEY, " +
                    Contract.PurchaseEntry.COLUMN_NAME + " TEXT UNIQUE NOT NULL, "+
                    Contract.PurchaseEntry.COLUMN_QUANTITY + " INTEGER, "+
                    Contract.PurchaseEntry.COLUMN_RATE + " INTEGER, "+
                    Contract.PurchaseEntry.COLUMN_DATE + " TEXT NOT NULL, "+
                    Contract.PurchaseEntry.COLUMN_TOTAL + " INTEGER);";
        }
    }
}
