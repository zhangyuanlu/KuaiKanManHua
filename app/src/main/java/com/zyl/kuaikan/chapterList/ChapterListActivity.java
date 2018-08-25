package com.zyl.kuaikan.chapterList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.zyl.kuaikan.R;
import com.zyl.kuaikan.adapter.ChapterListAdapter;
import com.zyl.kuaikan.base.BaseActivity;
import com.zyl.kuaikan.bean.ChapterListBean;
import com.zyl.kuaikan.content.ContentActivity;
import com.zyl.kuaikan.view.CustomRecyclerView;

public class ChapterListActivity extends BaseActivity<ChapterListContract.Presenter> implements ChapterListContract.View,ChapterListAdapter.OnRecyclerViewItemClickListener {
    private static final String TAG="ChapterListActivity";
    private String url;
    private ChapterListAdapter chapterListAdapter;
    private CustomRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_list);

        url=getIntent().getStringExtra("url");
        initView();
        presenter.loadChapterList(url);
    }

    private void initView(){
        super.initView(this);
        recyclerView=findViewById(R.id.recyclerView);
        chapterListAdapter=new ChapterListAdapter(this);
        chapterListAdapter.setOnRecyclerViewItemClickListener(this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        View emptyView=findViewById(R.id.empty_view);
        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(chapterListAdapter);
    }

    @Override
    public ChapterListContract.Presenter initPresenter() {
        return new ChapterListPresenter(this);
    }

    @Override
    public void setChapterList(ChapterListBean bean) {
        chapterListAdapter.bindData(bean);
        chapterListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickChapter(String url) {
        Intent intent=new Intent(this, ContentActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }

    @Override
    public void onFollow() {
        Log.e(TAG,"onfollow");
    }

    @Override
    public void onFirstChapter(String url) {
        Intent intent=new Intent(this, ContentActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }

    @Override
    public void onCommonCartoon(String url) {
        presenter.loadChapterList(url);
    }
}
