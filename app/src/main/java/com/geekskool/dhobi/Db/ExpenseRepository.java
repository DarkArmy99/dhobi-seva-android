package com.geekskool.dhobi.Db;

import com.geekskool.dhobi.Helpers.DbConstants;
import com.geekskool.dhobi.Models.Expense;
import com.geekskool.dhobi.Models.Student;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by manisharana on 7/3/17.
 */

public class ExpenseRepository extends Repository{


    public RealmResults<Expense> getAllExpenses(String studentId) {
        return getStudent(studentId).getExpenses().sort(DbConstants.expenseDate);
    }

    private Student getStudent(String studentId) {
        return new StudentRepository().getStudent(studentId);
    }

    public void addExpense(String studentId, final Expense expense, final Realm.Transaction.OnSuccess callback) {
        final Student student = getStudent(studentId);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Expense expenseRow = realm.copyToRealmOrUpdate(expense);
                student.getExpenses().add(expenseRow);
                if (callback != null)
                    callback.onSuccess();
            }
        });
    }
}
