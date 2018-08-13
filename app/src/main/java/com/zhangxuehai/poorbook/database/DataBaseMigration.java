package com.zhangxuehai.poorbook.database;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

/** 数据库升级文件
 * 创建于2018/7/11 作者 章学海.
 */
public final class DataBaseMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        while (oldVersion<newVersion){
            int version= (int) oldVersion;
            switch (version){
                case 1:
                    updata2version_1_0_0_1(realm);
                    break;
            }
            oldVersion++;
        }
    }

    private void updata2version_1_0_0_1(DynamicRealm realm){

    }
}
