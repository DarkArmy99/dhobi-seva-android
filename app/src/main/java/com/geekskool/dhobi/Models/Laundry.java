package com.geekskool.dhobi.Models;

/**
 * Created by manisharana on 16/2/17.
 */
public class Laundry {

    private String id;
    private String name = "Laundry";
    private int quantity;
    private float rate;
    private float total;
    private long date;

    public Laundry(){}
    public Laundry(int quantity, float rate, float total, long date) {
        this.quantity = quantity;
        this.rate = rate;
        this.total = total;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getRate() {
        return rate;
    }

    public float getTotal() {
        return total;
    }

    public long getDate() {
        return date;
    }
}
