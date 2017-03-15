package com.geekskool.dhobi.Db;

import com.geekskool.dhobi.Models.Course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

import static org.junit.Assert.*;

public class CourseRepositoryTest {

    private CourseRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new CourseRepository();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void sanityTest() throws Exception {

        // From: http://stackoverflow.com/questions/35153240/realm-unit-test-on-android
        //
//        Unit tests are hard or impossible when using Realm in the class that you are testing (thanks Dmitry for mentioning). What I can do is run the tests as instrumental tests (thanks Dmitry, Christian).
//
//                And that is quite easy, I won't have to change anything to the test methods...
//
//        A. Move the test class into an "androidTest" folder, instead of "test". (Since Android Studio 1.1 you should put your Unit tests in /src/test and Android Instrumentation Tests in /src/androidTest)
//
//        B. Add the dependencies for instrumental tests in the gradle build file, use "androidTest" because they're instrumental:
//
//        androidTestCompile 'junit:junit:4.12'
//        androidTestCompile 'io.reactivex:rxjava:1.1.0'
//        androidTestCompile 'com.android.support.test:runner:0.4.1'
//        androidTestCompile 'com.android.support.test:rules:0.4.1'
//        androidTestCompile 'com.android.support:support-annotations:23.1.1'
//        C. In the test class, replace the runner at the top with AndroidJUnit4:
//
//        @RunWith(AndroidJUnit4.class)
//        public class MyClassTest extends TestCase {
//...
//            Create an Android run configuration of type "Android Tests", run it and voila, it will test the same methods fine now, but on a device. Makes me very happy.

        List<Course> expected = new ArrayList<>();
        Course course1 = new Course(10, "Dhamma Pith", Calendar.getInstance().getTimeInMillis(), Calendar.getInstance().getTimeInMillis(), "zig");
        Course course2 = new Course(10, "Dhamma Pith 2", Calendar.getInstance().getTimeInMillis(), Calendar.getInstance().getTimeInMillis(), "course 2");
        expected.add(course1);
        expected.add(course2);

        repository.add(course1, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

            }
        });
        repository.add(course2, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

            }
        });

        List<Course> actual = repository.getAll();
        assertEquals(expected, actual);
    }
}
