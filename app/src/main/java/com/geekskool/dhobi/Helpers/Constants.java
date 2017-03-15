package com.geekskool.dhobi.Helpers;

/**
 * Created by manisharana on 24/2/17.
 */

public class Constants {
    private static final int MINS = 60;
    private static final int SECONDS = 60;
    private static final int MILLIS = 1000;
    private static final int HOURS = 24;
    public static final int MIN_IN_MILLIS =  SECONDS * MILLIS;
    private static final int HRS_TO_MILLIS = HOURS * MINS * MIN_IN_MILLIS;

    // for intents
    public static final String COURSE_ID = "COURSE_ID";
    public static final String STUDENT_ID = "STUDENT_ID";
    public static final String EMPTY_STRING = "";

    public static final String FILE_MIME_TYPE = "application/pdf";

    //for displaying result
    public static final String RESULT_NAME = "Name : ";
    public static final String RESULT_ROOM_NUMBER = "Room number : ";
    public static final String RESULT_EXPENSE = "Expense : ";
    public static final String RESULT_LAUNDRY = "Laundry : ";
    public static final String RESULT_DEPOSIT = "Deposit : ";
    public static final String RESULT_BALANCE = "Balance : ";

    public static long getMilliseconds(Integer days) {
        return days * HRS_TO_MILLIS;
    }
}
