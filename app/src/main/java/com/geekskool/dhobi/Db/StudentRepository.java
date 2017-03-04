package com.geekskool.dhobi.Db;

import com.geekskool.dhobi.Helpers.DbConstants;
import com.geekskool.dhobi.Models.Course;
import com.geekskool.dhobi.Models.Expense;
import com.geekskool.dhobi.Models.Student;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by manisharana on 1/3/17.
 */

public class StudentRepository {

    private Realm realm = Realm.getDefaultInstance();

    public void addStudent(String courseId, final Student student, final Realm.Transaction.OnSuccess callback) {
        final Course course = new CourseRepository().get(courseId);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Student studRow = realm.copyToRealmOrUpdate(student);
                course.getStudents().add(studRow);
                if (callback != null)
                    callback.onSuccess();
            }
        });
    }

    public Student getStudent(String id) {
        return realm.where(Student.class).equalTo(DbConstants.studentID, id).findFirst();
    }


    public RealmResults<Student> getAllStudents(String courseId) {
        Course course = realm.where(Course.class).equalTo(DbConstants.courseID, courseId).findFirst();
        RealmList<Student> students = course.getStudents();
        return students.sort(DbConstants.studentName, Sort.ASCENDING);
    }

    public void close() {
        realm.close();
    }


    public RealmResults<Expense> getAllExpenses(String studentId) {
        return getStudent(studentId).getExpenses().sort(DbConstants.expenseDate);
    }
}
