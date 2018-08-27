package com.zhangxuehai.poorbook.model.bookpick;

import android.app.AlertDialog;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmuiteam.qmui.util.QMUISpanHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogView;
import com.zhangxuehai.poorbook.BaseActivity;
import com.zhangxuehai.poorbook.R;
import com.zhangxuehai.poorbook.conf.Page;
import com.zhangxuehai.poorbook.database.table.DBBook;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

@Route(path = Page.帐本选择)
public class BookPickActivity extends BaseActivity<BookPickViewModel> {
    @BindView(R.id.topbar)
    QMUITopBar topBar;
    @BindView(R.id.list)
    RecyclerView list;
    View listEmpty;

    BookPickAdapter adapter;
    QMUIDialog addDialog;

    @Override
    protected int getContentViewRes() {
        return R.layout.activity_book_pick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //QMUIStatusBarHelper.translucent(this);
        topBar.setTitle("帐本选择");
        topBar.setBackgroundResource(R.color.colorAccent);
        topBar.addLeftBackImageButton();
        topBar.addRightTextButton("添加账本",R.id.add)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showAddBookDialog();
                    }
                });

        adapter=new BookPickAdapter(getViewmodel().getBooks());
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        listEmpty=getLayoutInflater().inflate(R.layout.item_book_pick_empty,null);
        listEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddBookDialog();
            }
        });
        adapter.setEmptyView(listEmpty);
        list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        list.setAdapter(adapter);
        PagerSnapHelper snapHelper=new PagerSnapHelper();
        snapHelper.attachToRecyclerView(list);
    }

    @Override
    protected void onDestroy() {
        if(addDialog!=null&&addDialog.isShowing()){
            addDialog.cancel();
        }
        super.onDestroy();
    }

    private void showAddBookDialog(){
        final QMUIDialog.EditTextDialogBuilder builder=new QMUIDialog.EditTextDialogBuilder(this);
        addDialog=builder
                .setTitle("新增账本")
                .setPlaceholder("请输入账本名称")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("创建", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        final String name=builder.getEditText().getText().toString();
                        if(name.length()>0){
                            dialog.dismiss();
                            getViewmodel().getRealm().executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    try{
                                        realm.insert(new DBBook(name));
                                    }catch (RealmPrimaryKeyConstraintException e){
                                        showMsg("改账本名字已经存在");
                                    }
                                }
                            });
                        }
                    }
                })
                .show();
    }
}
