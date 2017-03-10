package com.geekskool.dhobi.Helpers;

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

}
