package com.geekskool.dhobi.Models;

/**
 * Created by manisharana on 16/2/17.
 */
public class Purchase{

    private String id;
    private String name;
    private int quantity;
    private float rate;
    private float total;
    private long date;

    public Purchase(){}
    public Purchase(String name, int quantity, float rate, float total, long date) {
        this.name = name;
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
