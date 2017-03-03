package com.geekskool.dhobi;

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

    public static long getMilliseconds(Integer days) {
        return days * HRS_TO_MILLIS;
    }
}
