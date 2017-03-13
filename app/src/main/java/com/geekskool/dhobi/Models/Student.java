package com.geekskool.dhobi.Models;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by manisharana on 16/2/17.
 */
public class Student  extends RealmObject {

    @PrimaryKey
    @Required
    private String id;
    @Required
    private String name;

    @Required
    private String roomNumber;

    @Required
    private String seatNumber;

    private RealmList<Expense> expenses;

    public Student(){}
    public Student(String name, String roomNumber,String seatNumber) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.expenses = new RealmList<>();
        this.roomNumber = roomNumber;
        this.seatNumber = seatNumber;
    }

    public String getName() {
        return name;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public RealmList<Expense> getExpenses() {
        return expenses;
    }

    public String getId() {
        return id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
}
