package com.zhangxuehai.poorbook;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import butterknife.ButterKnife;
import io.realm.Realm;

public abstract class BaseActivity<T extends ViewModel> extends AppCompatActivity {
    private Class<T> viewmodelClass;
    private T viewmodel;
    private QMUIDialog msgDialog;


    protected abstract @LayoutRes int getContentViewRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        if(msgDialog!=null&&msgDialog.isShowing()){
            msgDialog.cancel();
        }
        super.onDestroy();
    }

    protected boolean hasBack(){return true;}
    protected boolean hasTitle(){return false;}
    protected void initToolbar(Toolbar toolbar){
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            if(hasBack()){
                actionBar.setHomeButtonEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            if(!hasTitle()){
                actionBar.setDisplayShowTitleEnabled(false);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }

    protected void showMsg(String str){
        QMUIDialog.MessageDialogBuilder builder = new QMUIDialog.MessageDialogBuilder(this);
        msgDialog=builder.setMessage(str).show();
    }
}
