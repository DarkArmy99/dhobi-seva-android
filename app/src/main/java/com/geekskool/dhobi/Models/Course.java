package com.geekskool.dhobi.Models;

import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by manisharana on 16/2/17.
 */
public class Course extends RealmObject{

    @PrimaryKey
    @Required
    private String id;

    private int duration;

    private RealmList<Student> students;

    @Required
    private String location;

    private long startDate;

    private long endDate;

    @Required
    private String description;

    public Course(){}
    public Course(int duration, String location, long startDate, long endDate, String description) {
        this.id = UUID.randomUUID().toString();
        this.duration = duration;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.students = new RealmList<>();
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
    public String getId() {
        return id;
    }
}
