package com.geekskool.dhobi.Providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.geekskool.dhobi.Contracts.Contract;
import com.geekskool.dhobi.DbHelper;

/**
 * Created by manisharana on 17/2/17.
 */
public class DataProvider extends ContentProvider {


    private static final int COURSE = 100;
    private static final int STUDENT = 200;
    private static final int PURCHASE = 300;
    private static final int UPDATE_COURSE = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        String content = Contract.CONTENT_AUTHORITY;
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(content, Contract.PATH_COURSE, COURSE);
        matcher.addURI(content, Contract.PATH_STUDENT, STUDENT);
        matcher.addURI(content, Contract.PATH_PURCHASE, PURCHASE);

        return matcher;
    }

    private DbHelper mOpenHelper;

    @Override

    public boolean onCreate() {
        mOpenHelper = new DbHelper(getContext());
        return true;
    }




    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Cursor retCursor;

        switch(sUriMatcher.match(uri)) {
            case COURSE:
                retCursor = db.query(
                        Contract.CourseEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case STUDENT:
                retCursor = db.query(
                        Contract.StudentEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case PURCHASE:
                retCursor = db.query(
                        Contract.PurchaseEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch(sUriMatcher.match(uri)){
            case COURSE:
                return Contract.CourseEntry.CONTENT_TYPE;
            case PURCHASE:
                return Contract.PurchaseEntry.CONTENT_TYPE;
            case STUDENT:
                return Contract.StudentEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        long _id;
        Uri returnUri;

        switch (sUriMatcher.match(uri)) {
            case COURSE:
                _id = db.insert(Contract.CourseEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = Contract.CourseEntry.buildCourseUri(_id);
                else
                    throw new UnsupportedOperationException("Unable to insert rows into: " + uri);
                break;
            case STUDENT:
                _id = db.insert(Contract.StudentEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = Contract.StudentEntry.buildCourseUri(_id);
                else
                    throw new UnsupportedOperationException("Unable to insert rows into: " + uri);
                break;
            case PURCHASE:
                _id = db.insert(Contract.PurchaseEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = Contract.PurchaseEntry.buildCourseUri(_id);
                else
                    throw new UnsupportedOperationException("Unable to insert rows into: " + uri);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
