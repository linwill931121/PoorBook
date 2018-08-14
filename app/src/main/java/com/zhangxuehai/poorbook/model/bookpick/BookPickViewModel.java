package com.zhangxuehai.poorbook.model.bookpick;

import com.zhangxuehai.poorbook.RealmViewModel;
import com.zhangxuehai.poorbook.database.table.DBBook;
import io.realm.RealmResults;

/**
 * 创建于2018/8/14 作者 章学海.
 */
public class BookPickViewModel extends RealmViewModel {

    public RealmResults<DBBook> getBooks(){
        return realm.where(DBBook.class).findAll();
    }
}
