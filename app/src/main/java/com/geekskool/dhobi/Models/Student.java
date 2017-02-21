package com.geekskool.dhobi.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by manisharana on 16/2/17.
 */
public class Student implements Serializable {

    private String name;
    private boolean isPreviousStudent;
    private String gender;   // convert to enum
    private int age;
    private int deposit;
    private ArrayList<Purchase> purchases;
    private ArrayList<Laundry> laundryList;
    private String roomNumber;

    public Student(String name, boolean isPreviousStudent, String gender, int age, int deposit, ArrayList<Purchase> purchases, ArrayList<Laundry> laundryList, String roomNumber) {
        this.name = name;
        this.isPreviousStudent = isPreviousStudent;
        this.gender = gender;
        this.age = age;
        this.deposit = deposit;
        this.purchases = purchases;
        this.laundryList = laundryList;
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPreviousStudent() {
        return isPreviousStudent;
    }

    public void setPreviousStudent(boolean previousStudent) {
        isPreviousStudent = previousStudent;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(ArrayList<Purchase> purchases) {
        this.purchases = purchases;
    }

    public ArrayList<Laundry> getLaundryList() {
        return laundryList;
    }

    public void setLaundryList(ArrayList<Laundry> laundryList) {
        this.laundryList = laundryList;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
