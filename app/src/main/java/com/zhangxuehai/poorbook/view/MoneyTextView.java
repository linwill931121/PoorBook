package com.zhangxuehai.poorbook.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * 创建于2018/8/13 作者 章学海.
 */
public class MoneyTextView extends AppCompatTextView implements ValueAnimator.AnimatorUpdateListener{
    DecimalFormat format=new DecimalFormat("###,###,##0.00");
    float money=0f;
    ValueAnimator animator;

    public MoneyTextView(Context context) {
        super(context);
    }

    public MoneyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MoneyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMoney(float money) {
        if(animator!=null&&animator.isRunning()){
            animator.cancel();
        }
        animator=ValueAnimator.ofFloat(this.money,money);
        animator.setDuration(1200);
        animator.addUpdateListener(this);
        animator.start();
        this.money = money;
    }
    private void setMoneyText(float money){
        setText(format.format(money));
    }


    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float money= (float) valueAnimator.getAnimatedValue();
        setMoneyText(money);
    }
}
