package com.geekskool.dhobi;


import android.app.Application;

import com.geekskool.dhobi.Helpers.DbConstants;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class VipassanaApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name(DbConstants.FILENAME)
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

}
