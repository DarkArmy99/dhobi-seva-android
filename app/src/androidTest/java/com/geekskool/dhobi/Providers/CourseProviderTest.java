package com.geekskool.dhobi.Providers;

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
public class CourseProviderTest extends ProviderTestCase2<CourseProvider> {

    private ContentValues values;

    public CourseProviderTest() {
        super(CourseProvider.class, Contract.CONTENT_AUTHORITY);
    }

    @Before
    @Override
    public void setUp() throws Exception {
        setContext(InstrumentationRegistry.getTargetContext());
        super.setUp();
        values = new ContentValues();
        String cName = "SampleName";
        int days = 10;
        String loc = "bangalore";
        Calendar cal = Calendar.getInstance();

        values.put(COLUMN_NAME, cName);
        values.put(COLUMN_START_DATE, cal.getTimeInMillis());
        values.put(COLUMN_END_DATE, cal.getTimeInMillis() + 1000);
        values.put(COLUMN_DURATION, days);
        values.put(COLUMN_LOCATION, loc);
    }

    @Test
    public void testShouldBeAbleToInsertCourse() {
        Uri uri = getMockContentResolver().insert(Contract.CourseEntry.CONTENT_URI, values);
        long id = ContentUris.parseId(uri);

        assertNotNull(uri);
        assertTrue(id > 0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldNotBeAbleToInsertCourse() {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, "SampleName");
        getMockContentResolver().insert(Contract.CourseEntry.CONTENT_URI, cv);
    }

}