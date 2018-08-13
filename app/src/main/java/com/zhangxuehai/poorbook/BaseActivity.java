package com.zhangxuehai.poorbook;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import io.realm.Realm;

public abstract class BaseActivity extends AppCompatActivity {
    protected Realm realm;

    protected abstract @LayoutRes int getContentViewRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm=Realm.getDefaultInstance();
        setContentView(getContentViewRes());
        ButterKnife.bind(this);//activity不需要手动解绑
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
