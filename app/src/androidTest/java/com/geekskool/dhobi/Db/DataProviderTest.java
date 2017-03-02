package com.geekskool.dhobi.Db;

import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ProviderTestCase2;

import com.geekskool.dhobi.Contracts.Contract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static com.geekskool.dhobi.Contracts.Contract.CourseEntry.COLUMN_DURATION;
import static com.geekskool.dhobi.Contracts.Contract.CourseEntry.COLUMN_END_DATE;
import static com.geekskool.dhobi.Contracts.Contract.CourseEntry.COLUMN_LOCATION;
import static com.geekskool.dhobi.Contracts.Contract.CourseEntry.COLUMN_NAME;
import static com.geekskool.dhobi.Contracts.Contract.CourseEntry.COLUMN_START_DATE;

/**
 * Created by manisharana on 22/2/17.
 */
@RunWith(AndroidJUnit4.class)
public class DataProviderTest extends ProviderTestCase2<DataProvider> {

    public DataProviderTest() {
        super(DataProvider.class, Contract.CONTENT_AUTHORITY);
    }

    @Before
    public void setUp() throws Exception {
        setContext(InstrumentationRegistry.getTargetContext());
        super.setUp();
    }

    @Test
    public void test_should_be_able_to_insert_course() {
        Uri uri = getMockContentResolver().insert(Contract.CourseEntry.CONTENT_URI, getValidValues());

        long id = ContentUris.parseId(uri);

        assertNotNull(uri);
        assertTrue(id > 0);
    }


    @Test(expected = UnsupportedOperationException.class)
    public void should_throw_unsupported_operation_exception_if_course_fields_are_null() {
        getMockContentResolver().insert(Contract.CourseEntry.CONTENT_URI, getNullValues());
    }

    private ContentValues getNullValues() {
        ContentValues cv = new ContentValues();
        cv.putNull(COLUMN_NAME);
        cv.putNull(COLUMN_START_DATE);
        cv.putNull(COLUMN_END_DATE);
        cv.putNull(COLUMN_DURATION);
        cv.putNull(COLUMN_LOCATION);
        return cv;
    }

    private ContentValues getValidValues() {
        ContentValues cv = new ContentValues();
        Calendar cal = Calendar.getInstance();
        cv.put(COLUMN_NAME, "SampleName");
        cv.put(COLUMN_START_DATE, cal.getTimeInMillis());
        cv.put(COLUMN_END_DATE, cal.getTimeInMillis() + 1000);
        cv.put(COLUMN_DURATION, 10);
        cv.put(COLUMN_LOCATION, "bangalore");
        return cv;
    }

}