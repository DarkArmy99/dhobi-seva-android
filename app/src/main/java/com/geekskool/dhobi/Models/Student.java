package com.geekskool.dhobi.Models;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by manisharana on 16/2/17.
 */
public class Student  extends RealmObject implements Serializable {

    @PrimaryKey
    @Required
    private String id;
    @Required
    private String name;
    @Required
    private int deposit;
    @Required
    private String roomNumber;

    private Finance finance;

    public Student(String name, int deposit,Finance finance, String roomNumber) {
        this.name = name;
        this.deposit = deposit;
        this.finance = finance;
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public int getDeposit() {
        return deposit;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Finance getFinance() {
        return finance;
    }
}
