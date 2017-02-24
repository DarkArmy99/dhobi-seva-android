package com.geekskool.dhobi.Models;

import java.util.ArrayList;

/**
 * Created by manisharana on 24/2/17.
 */

public class Finance {

    private ArrayList<Purchase> purchases;
    private ArrayList<Laundry> laundryList;

    public Finance(ArrayList<Purchase> purchases, ArrayList<Laundry> laundryList) {
        this.purchases = purchases;
        this.laundryList = laundryList;
    }
}
