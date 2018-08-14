package com.zhangxuehai.poorbook;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import butterknife.ButterKnife;
import io.realm.Realm;

public abstract class BaseActivity<T extends ViewModel> extends AppCompatActivity {
    protected Realm realm;
    private Class<T> viewmodelClass;
    private T viewmodel;


    protected abstract @LayoutRes int getContentViewRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm=Realm.getDefaultInstance();
        setContentView(getContentViewRes());
        ButterKnife.bind(this);//activity不需要手动解绑

        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        viewmodelClass = (Class) params[0];
        viewmodel= ViewModelProviders.of(this).get(viewmodelClass);
    }

    protected T getViewmodel(){return viewmodel;}

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
