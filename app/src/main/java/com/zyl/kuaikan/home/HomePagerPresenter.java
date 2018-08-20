package com.zyl.kuaikan.home;

import android.util.Log;

import com.zyl.kuaikan.API.RetrofitFactory;
import com.zyl.kuaikan.base.BasePresenterImpl;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class HomePagerPresenter extends BasePresenterImpl<HomePageContract.View> implements HomePageContract.Presenter{
    private static final String TAG="HomePagerPresenter";
    public HomePagerPresenter(HomePageContract.View view){
        super(view);
    }

    @Override
    public void loadPopCartoons(boolean forceUpdate,int index) {
        RetrofitFactory.getInstance()
                .getPopCartoons(index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                        view.showloadingDialog("");
                    }
                })
                .subscribe(new Consumer<List>() {
                    @Override
                    public void accept(List list) throws Exception {
                        view.dismissLoadingDialog();
                        view.setDayPops(list);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.dismissLoadingDialog();
                        Log.e(TAG,throwable.toString());
                        throwable.printStackTrace();
                    }
                });
    }
}
