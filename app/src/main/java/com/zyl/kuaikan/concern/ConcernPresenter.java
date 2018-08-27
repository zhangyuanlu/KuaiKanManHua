package com.zyl.kuaikan.concern;

import android.util.Log;

import com.zyl.kuaikan.api.RetrofitFactory;
import com.zyl.kuaikan.base.BasePresenterImpl;
import com.zyl.kuaikan.bean.UserTopicsBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ConcernPresenter extends BasePresenterImpl<ConcernContract.View>implements ConcernContract.Presenter{
    private static final String TAG="ConcernPresenter";
    public ConcernPresenter(ConcernContract.View view){
        super(view);
    }
    @Override
    public void loadConcern() {
        RetrofitFactory.getInstance(RetrofitFactory.TYPE_GET_TOPICS)
                .getUserTpoics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                    }
                })
                .subscribe(new Consumer<UserTopicsBean>() {
                    @Override
                    public void accept(UserTopicsBean topicsBean) throws Exception {
                        if(topicsBean.getData().getTopics()==null){
                            view.noData(topicsBean.getMessage());
                        }else{
                            view.setConcern(topicsBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG,throwable.toString());
                        throwable.printStackTrace();
                    }
                });
    }
}
