package com.geekskool.dhobi.Models;

import java.io.Serializable;

/**
 * Created by manisharana on 16/2/17.
 */
public class Student implements Serializable {

    private String name;
    private boolean isOldStudent;
    private String gender;   // convert to enum
    private int age;
    private int deposit;
    private String roomNumber;
    private Finance finance;

    public Student(String name, boolean isOldStudent, String gender, int age, int deposit,Finance finance, String roomNumber) {
        this.name = name;
        this.isOldStudent = isOldStudent;
        this.gender = gender;
        this.age = age;
        this.deposit = deposit;
        this.finance = finance;
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public boolean isOldStudent() {
        return isOldStudent;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
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
