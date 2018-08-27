package com.zhangxuehai.poorbook.database.table;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**账本
 * 创建于2018/8/13 作者 章学海.
 */
public class DBBook extends RealmObject{
    public DBBook() {
    }

    public DBBook(String bookName) {
        this.bookName = bookName;
        createTime=new Date();
    }

    @PrimaryKey
    public String bookName;
    public Date createTime;
}
