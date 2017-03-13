package com.geekskool.dhobi.Helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by manisharana on 22/2/17.
 */
public class Util {

    public static boolean isValid(String name) {
        return name != null && name.trim().length() != 0;
    }

    public static boolean isInvalid(String name) {
        return !isValid(name);
    }

    public static String getDateInFormat(long date) {
        Date curr = new Date(date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        return sdf.format(curr);
    }

}
