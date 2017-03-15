package com.geekskool.dhobi.Db;

import com.geekskool.dhobi.Helpers.DbConstants;
import com.geekskool.dhobi.Models.Course;
import com.geekskool.dhobi.Models.Expense;
import com.geekskool.dhobi.Models.Result;
import com.geekskool.dhobi.Models.ResultMap;
import com.geekskool.dhobi.Models.Student;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.Realm.Transaction.OnSuccess;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.geekskool.dhobi.Helpers.DbConstants.expenseAmount;
import static com.geekskool.dhobi.Helpers.DbConstants.expenseDeposit;
import static com.geekskool.dhobi.Helpers.DbConstants.expenseLaundry;
import static com.geekskool.dhobi.Helpers.DbConstants.expenseName;
import static com.geekskool.dhobi.Helpers.DbConstants.studentName;

/**
 * Created by manisharana on 1/3/17.
 */

public class StudentRepository extends Repository {

    public void addStudent(String courseId, final Student student, final OnSuccess callback) {
        final Course course = getCourse(courseId);
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

    private Course getCourse(String courseId) {
        return new CourseRepository().get(courseId);
    }

    Student getStudent(String studentId) {
        return realm.where(Student.class).equalTo(DbConstants.studentID, studentId).findFirst();
    }

    public RealmResults<Student> getAllStudents(String courseId) {
        return getCourse(courseId).getStudents().sort(studentName, Sort.ASCENDING);
    }

    private Number getDeposit(RealmList<Expense> expenseList) {
        return expenseList.where().equalTo(expenseName, expenseDeposit).sum(expenseAmount);
    }

    private Number getLaundryExpense(RealmList<Expense> expenseList) {
        return expenseList.where().equalTo(expenseName, expenseLaundry).sum(expenseAmount);
    }

    private Number getOtherExpenses(RealmList<Expense> expenseList) {
        return expenseList.where().notEqualTo(expenseName, expenseLaundry).notEqualTo(expenseName, expenseDeposit).sum(expenseAmount);
    }

    public ResultMap getResults(String courseId) {
        ArrayList<Result> results = new ArrayList<>();
        Course course = getCourse(courseId);
        RealmResults<Student> students =  course.getStudents().sort(studentName, Sort.ASCENDING);


        for(Student student : students){
            RealmList<Expense> expenseList = getStudent(student.getId()).getExpenses();
            Number deposit = getDeposit(expenseList); ;
            Number laundry = getLaundryExpense(expenseList);
            Number expenses = getOtherExpenses(expenseList);
            Number balance = deposit.intValue()- laundry.intValue() - expenses.intValue();
            results.add(new Result(student.getName(),String.valueOf(expenses), String.valueOf(laundry), String.valueOf(deposit), String.valueOf(balance), student.getRoomNumber()));
        }
        return new ResultMap(results,course.getDescription());
    }
}
