package com.geekskool.dhobi.Models;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by manisharana on 16/2/17.
 */
public class Course extends RealmObject implements Serializable {

    @PrimaryKey
    @Required
    private String id;

    @Required
    private int duration;

    private RealmList<Student> students;

    @Required
    private String location;

    @Required
    private long startDate;

    @Required
    private long endDate;

    @Required
    private String description;

    public Course(int duration, String location, long startDate, long endDate, String description) {
        this.duration = duration;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public RealmList<Student> getStudents() {
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

    public String getDescription() {
        return description;
    }

}
