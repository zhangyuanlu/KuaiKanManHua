package com.zyl.kuaikan.concern;

import android.os.Bundle;

import com.zyl.kuaikan.R;
import com.zyl.kuaikan.base.BaseActivity;

public class ConcernActivity extends BaseActivity<ConcernContract.Presenter> implements ConcernContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concern);
    }

    @Override
    public ConcernContract.Presenter initPresenter() {
        return new ConcernPresenter(this);
    }

    @Override
    public void setConcern() {

    }
}
