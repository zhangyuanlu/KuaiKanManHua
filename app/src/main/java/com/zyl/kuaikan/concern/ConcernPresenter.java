package com.zyl.kuaikan.concern;

import com.zyl.kuaikan.base.BasePresenterImpl;

public class ConcernPresenter extends BasePresenterImpl<ConcernContract.View>implements ConcernContract.Presenter{
    private static final String TAG="ConcernPresenter";
    public ConcernPresenter(ConcernContract.View view){
        super(view);
    }
    @Override
    public void loadConcern() {

    }
}
