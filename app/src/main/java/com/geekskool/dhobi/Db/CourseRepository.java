package com.geekskool.dhobi.Db;

import com.geekskool.dhobi.Helpers.DbConstants;
import com.geekskool.dhobi.Models.Course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnSuccess;
import io.realm.RealmResults;
import io.realm.Sort;

public class CourseRepository extends Repository{

    public void add(final Course course, final OnSuccess callback){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(course);
                if (callback != null)
                    callback.onSuccess();
            }
        });
    }

    public Course get(String courseId){
        return realm.where(Course.class).equalTo(DbConstants.courseID, courseId).findFirst();
    }

    public List<Course> getAll() {
        // TODO: find a less gross way of doing this
        Course[] courses = (Course[]) realm.where(Course.class).findAllSorted(DbConstants.courseStartDate, Sort.ASCENDING).toArray();
        return Arrays.asList(courses);
    }

}
