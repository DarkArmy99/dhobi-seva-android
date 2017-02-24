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

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String getLocation() {
        return location;
    }

    public long getStartDate() {
        return startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public String getDesc() {
        return desc;
    }

}
