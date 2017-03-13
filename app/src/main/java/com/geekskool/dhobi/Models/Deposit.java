package com.geekskool.dhobi.Models;

import java.util.UUID;

/**
 * Created by manisharana on 3/3/17.
 */
public class Deposit  {

    private String id;
    private String name = "Deposit";
    private long date;

    Deposit(){}
    Deposit(long date){
        this.id = UUID.randomUUID().toString();
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
