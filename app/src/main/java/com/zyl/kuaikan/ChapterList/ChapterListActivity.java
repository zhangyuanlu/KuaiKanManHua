package com.zyl.kuaikan.ChapterList;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zyl.kuaikan.R;
import com.zyl.kuaikan.adapter.ChapterListAdapter;
import com.zyl.kuaikan.base.BaseActivity;
import com.zyl.kuaikan.bean.ChapterListBean;

public class ChapterListActivity extends BaseActivity<ChapterListContract.Presenter> implements ChapterListContract.View {
    private static final String TAG="ChapterListActivity";
    private ChapterListBean listBean;
    private String url;
    private ChapterListAdapter chapterListAdapter;
    private RecyclerView recyclerView;

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
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(chapterListAdapter);
    }

    @Override
    public ChapterListContract.Presenter initPresenter() {
        return new ChapterListPresenter(this);
    }

    @Override
    public void setChapterList(ChapterListBean bean) {
        listBean=bean;
        chapterListAdapter.bindData(listBean);
        chapterListAdapter.notifyDataSetChanged();
    }
}
