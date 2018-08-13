package com.zhangxuehai.poorbook;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.zhangxuehai.poorbook.database.DataBaseIniter;

/**
 * 创建于2018/8/13 作者 章学海.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataBaseIniter.initDataBase(this);
        Utils.init(this);
    }
}
