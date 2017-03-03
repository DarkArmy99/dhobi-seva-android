package com.geekskool.dhobi.Models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by manisharana on 24/2/17.
 */

public class Finance extends RealmObject{

    private RealmList<Purchase> purchases;
  //  private RealmList<Laundry> laundryList;

    public Finance(){}

    public Finance(RealmList<Purchase> purchases) {
        this.purchases = purchases;
   //     this.laundryList = laundryList;
    }
}
