package com.zhangxuehai.poorbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.CacheUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.zhangxuehai.poorbook.conf.Page;
import com.zhangxuehai.poorbook.conf.Static;
import com.zhangxuehai.poorbook.database.table.DBBook;
import com.zhangxuehai.poorbook.view.MoneyTextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class InitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        String bookName=CacheUtils.getInstance().getString(Static.KEY_BOOK_PICK);
        if(StringUtils.isEmpty(bookName)){
            ARouter.getInstance().build(Page.帐本选择).navigation();
        }else{
            Realm realm=Realm.getDefaultInstance();
            DBBook book=realm.where(DBBook.class).equalTo("bookName",bookName).findFirst();
            book=realm.copyFromRealm(book);
            realm.close();
            if(book==null){
                ARouter.getInstance().build(Page.帐本选择).navigation();
            }else{
                ARouter.getInstance().build(Page.主页).navigation();
            }
        }
        finish();
    }
}
