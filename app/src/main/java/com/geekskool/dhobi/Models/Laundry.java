package com.geekskool.dhobi.Models;

/**
 * Created by manisharana on 16/2/17.
 */
public class Laundry extends Purchase{

    public Laundry(int quantity, float rate, float total, long date) {
        super("Laundry",quantity,rate,total,date);
    }

}
