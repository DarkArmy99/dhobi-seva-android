package com.geekskool.dhobi.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by manisharana on 16/2/17.
 */
public class Course implements Serializable{
    private int duration;
    private ArrayList<Student> students;
    private String location;
    private long startDate;
    private long endDate;
    private String desc;

    public Course(int duration, String location, long startDate, long endDate, String desc) {
        this.duration = duration;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.desc = desc;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
