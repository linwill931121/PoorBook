package com.zhangxuehai.poorbook.model.bookpick;

import android.support.annotation.Nullable;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhangxuehai.poorbook.R;
import com.zhangxuehai.poorbook.database.table.DBBook;

import java.util.List;

/**
 * 创建于2018/8/16 作者 章学海.
 */
public class BookPickAdapter extends BaseQuickAdapter<DBBook,BaseViewHolder> {
    public BookPickAdapter(@Nullable List<DBBook> data) {
        super(R.layout.item_book_pick, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, DBBook item) {
        helper.setText(R.id.bookname,item.bookName);
        helper.setText(R.id.time, TimeUtils.date2String(item.createTime));
    }
}
