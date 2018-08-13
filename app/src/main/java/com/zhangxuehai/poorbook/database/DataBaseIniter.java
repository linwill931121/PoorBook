package com.zhangxuehai.poorbook.database;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * 创建于2018/7/11 作者 章学海.
 */
public final class DataBaseIniter {
    public static void initDataBase(Context application){
        Realm.init(application);
        RealmConfiguration config=new RealmConfiguration.Builder()
                .name(DataBaseConfig.DB_NAME)
                .schemaVersion(DataBaseConfig.DB_VERSION)
                .migration(new DataBaseMigration())
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
