package com.zhangxuehai.poorbook.database.table;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**流水类型
 * 创建于2018/8/13 作者 章学海.
 */
public class DBType extends RealmObject {
    @PrimaryKey
    String id= UUID.randomUUID().toString();
    @Required
    String name;

    DBBook book;
}
