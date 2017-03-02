package com.geekskool.dhobi.Db;

import com.geekskool.dhobi.Models.Course;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnSuccess;

/**
 * Created by manisharana on 1/3/17.
 */

public class CourseRepository {

    private Realm realm = Realm.getDefaultInstance();

    public void addCourse(final Course course, final OnSuccess callback){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(course);
                if (callback != null)
                    callback.onSuccess();
            }
        });

    }

    public Course getCourse(int id){
        return realm.where(Course.class).equalTo("id", id).findFirst();
    }

    public void stop(){
        realm.close();
    }
}
