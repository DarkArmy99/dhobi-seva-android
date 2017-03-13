package com.geekskool.dhobi.Models;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by manisharana on 4/3/17.
 */

public class Expense extends RealmObject{
    @Required
    @PrimaryKey
    private String id;

    @Required
    private String name;
    private int quantity;
    private float rate;
    private float amount;
    private long date;

    public Expense(){}

    public Expense(String name, int quantity, float rate, float amount, long date) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.quantity = quantity;
        this.rate = rate;
        this.amount = amount;
        this.date = date;
    }

    public Expense(String name, float amount, long date) {
        this(name,0,0,amount,date);
    }


    public String getId() {
        return id;
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

    public float getAmount() {
        return amount;
    }

    public long getDate() {
        return date;
    }
}
