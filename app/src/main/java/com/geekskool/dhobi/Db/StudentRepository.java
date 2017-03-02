package com.geekskool.dhobi.Db;

import com.geekskool.dhobi.Models.Student;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnSuccess;

/**
 * Created by manisharana on 1/3/17.
 */

public class StudentRepository {

    private Realm realm = Realm.getDefaultInstance();

    public void addStudent(Student student, OnSuccess callback){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

            }
        });
    }

    public Student getStudent(String id){
       return realm.where(Student.class).equalTo("id",id).findFirst();
    }

}
