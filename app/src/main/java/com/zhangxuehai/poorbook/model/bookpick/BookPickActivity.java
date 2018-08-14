package com.zhangxuehai.poorbook.model.bookpick;

import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhangxuehai.poorbook.BaseActivity;
import com.zhangxuehai.poorbook.R;
import com.zhangxuehai.poorbook.conf.Page;

@Route(path = Page.帐本选择)
public class BookPickActivity extends BaseActivity<BookPickViewModel> {

    @Override
    protected int getContentViewRes() {
        return R.layout.activity_book_pick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("帐本选择");
    }
}
