package com.zyl.kuaikan.content;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.zyl.kuaikan.R;
import com.zyl.kuaikan.adapter.ChapterContentAdapter;
import com.zyl.kuaikan.base.BaseActivity;
import com.zyl.kuaikan.bean.ChapterContentBean;
import com.zyl.kuaikan.view.CustomRecyclerView;

public class ContentActivity extends BaseActivity<ContentContract.Presenter> implements ContentContract.View,ChapterContentAdapter.onClickItemListener{
    private static final String TAG="ContentActivity";

    private CustomRecyclerView recyclerView;
    private ChapterContentAdapter contentAdapter;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        url=getIntent().getStringExtra("url");
        Log.e(TAG,"url="+url);
        initView();
        presenter.loadChapterContent(url);
    }

    private void initView(){
        super.initView(this);
        recyclerView=findViewById(R.id.recyclerView);
        View emptyView=findViewById(R.id.progressbar);
        contentAdapter=new ChapterContentAdapter(this);
        contentAdapter.setonClickItemListener(this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(contentAdapter);
    }
    @Override
    public ContentContract.Presenter initPresenter() {
        return new ContentPresenter(this);
    }

    @Override
    public void setChapterContent(ChapterContentBean bean) {
        contentAdapter.bindData(bean);
        contentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickNext(String url) {
        Log.e(TAG,"nexturl="+url);
    }

    @Override
    public void onClickLast(String url) {
        Log.e(TAG,"lasturl="+url);
    }
}
