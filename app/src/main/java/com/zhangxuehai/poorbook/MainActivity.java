package com.zhangxuehai.poorbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhangxuehai.poorbook.view.MoneyTextView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.money)
    MoneyTextView money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    float[] moneys=new float[]{0F,0.01F,0.1F,1F,1.2F,1000.03F,1000000F,10000000.64F};
    int index=0;
    @OnClick(R.id.button)
    void change(){
        index=index+1==moneys.length?0:index+1;
        money.setMoney(moneys[index]);
    }
}
