package com.zhangxuehai.poorbook.database.table;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 创建于2018/8/13 作者 章学海.
 */
public class DBLog extends RealmObject {
    @PrimaryKey
    public String id= UUID.randomUUID().toString();
    Date createTime;
    float cost;
    @Required
    DBType type;
    @Required
    DBBook book;
}
