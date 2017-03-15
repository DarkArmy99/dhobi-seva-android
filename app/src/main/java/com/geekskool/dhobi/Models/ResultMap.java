package com.geekskool.dhobi.Models;

import java.util.ArrayList;

/**
 * Created by manisharana on 4/3/17.
 */

public class ResultMap {

    private ArrayList<Result> results;
    private String courseName;

    public ResultMap(ArrayList<Result> results, String courseName) {
        this.results = results;
        this.courseName = courseName;
    }


    public ArrayList<Result> getResults() {
        return results;
    }

    public String getCourseName() {
        return courseName;
    }
}
