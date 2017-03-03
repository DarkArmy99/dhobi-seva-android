package com.geekskool.dhobi.Db;

import com.geekskool.dhobi.Helpers.DbConstants;
import com.geekskool.dhobi.Models.Course;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnSuccess;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by manisharana on 1/3/17.
 */
public class CourseRepository {

    private Realm realm = Realm.getDefaultInstance();

    public void add(final Course course, final OnSuccess callback){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(course);
                if (callback != null)
                    callback.onSuccess();
            }
        });

    }

    public Course get(int id){
        return realm.where(Course.class).equalTo(DbConstants.courseID, id).findFirst();
    }

    public RealmResults<Course> getAll(){
        RealmResults<Course> results = realm.where(Course.class).findAll();
        return results.sort(DbConstants.COURSE_START_DATE, Sort.ASCENDING);
    }

    public void close(){
        realm.close();
    }
}
