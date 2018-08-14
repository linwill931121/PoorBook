package com.zhangxuehai.poorbook;

import android.arch.lifecycle.ViewModel;

import io.realm.Realm;

/**
 * 创建于2018/8/14 作者 章学海.
 */
public class RealmViewModel extends ViewModel {
    protected Realm realm;
    public RealmViewModel() {
        realm=Realm.getDefaultInstance();
    }

    @Override
    protected void onCleared() {
        realm.close();
        super.onCleared();
    }
}
