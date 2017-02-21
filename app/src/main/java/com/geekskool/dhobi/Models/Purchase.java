package com.geekskool.dhobi.Models;

/**
 * Created by manisharana on 16/2/17.
 */
public class Purchase {
    private String name;
    private int quantity;
    private float rate;
    private float total;
    private long date;

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

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
