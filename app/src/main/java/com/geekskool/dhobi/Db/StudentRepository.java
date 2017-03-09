package com.geekskool.dhobi.Db;

import com.geekskool.dhobi.Helpers.DbConstants;
import com.geekskool.dhobi.Models.Student;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnSuccess;
import io.realm.RealmResults;

/**
 * Created by manisharana on 1/3/17.
 */

public class StudentRepository extends Repository {


    public void add(Student student, OnSuccess callback){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

            }
        });
    }

    public Student get(String id){
       return realm.where(Student.class).equalTo(DbConstants.studentID,id).findFirst();
    }

    public RealmResults<Student> getAll(){
        return realm.where(Student.class).findAll();
    }

}
