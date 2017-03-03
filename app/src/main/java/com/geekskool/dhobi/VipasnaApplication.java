package com.geekskool.dhobi;


import android.app.Application;

import com.geekskool.dhobi.Helpers.DbConstants;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class VipasnaApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name(DbConstants.FILENAME)
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

}
