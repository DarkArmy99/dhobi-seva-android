package com.geekskool.dhobi.Db;

import io.realm.Realm;

/**
 * Created by manisharana on 7/3/17.
 */
public class Repository {

    protected Realm realm = Realm.getDefaultInstance();

    public void close() {
        realm.close();
    }

}
