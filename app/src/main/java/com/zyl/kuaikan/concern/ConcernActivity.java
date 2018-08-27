package com.zyl.kuaikan.concern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.zyl.kuaikan.R;
import com.zyl.kuaikan.adapter.ConcernAdapter;
import com.zyl.kuaikan.base.BaseActivity;
import com.zyl.kuaikan.bean.UserTopicsBean;
import com.zyl.kuaikan.chapterList.ChapterListActivity;
import com.zyl.kuaikan.view.CustomRecyclerView;

public class ConcernActivity extends BaseActivity<ConcernContract.Presenter> implements ConcernContract.View , ConcernAdapter.onClickItemListener {
    private static final String TAG="ConcernActivity";
    private CustomRecyclerView recyclerView;
    private ConcernAdapter concernAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concern);
        initView();
        presenter.loadConcern();
    }
    private void initView(){
        super.initView(this);
        recyclerView=findViewById(R.id.recyclerView);
        View emptyView=findViewById(R.id.empty_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        concernAdapter=new ConcernAdapter(this);
        concernAdapter.setOnClickItemListener(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(concernAdapter);
    }
    @Override
    public ConcernContract.Presenter initPresenter() {
        return new ConcernPresenter(this);
    }

    @Override
    public void setConcern(UserTopicsBean topicsBean) {
        concernAdapter.bindData(topicsBean.getData().getTopics());
        concernAdapter.notifyDataSetChanged();
    }

    @Override
    public void noData(String msg) {
        Log.e(TAG,msg);
        showToastMsg(msg);
    }

    @Override
    public void onClickItem(int id) {
        Log.e(TAG,"id="+id);
        String url="web/topic/"+id;
        Intent intent=new Intent(this, ChapterListActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }

}
