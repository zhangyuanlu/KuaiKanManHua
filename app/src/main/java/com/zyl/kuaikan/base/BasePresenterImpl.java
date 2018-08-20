package com.zyl.kuaikan.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenterImpl<V extends IBaseView> implements IBasePresenter {
    protected V view;
    private CompositeDisposable compositeDisposable;
    public BasePresenterImpl(V view){
        this.view=view;
        start();
    }

    @Override
    public void detach() {
        this.view=null;
        unDisposable();
    }

    @Override
    public void start() {

    }

    @Override
    public void addDisposable(Disposable subscription) {
        if(compositeDisposable==null||compositeDisposable.isDisposed()){
            compositeDisposable=new CompositeDisposable();
        }
        compositeDisposable.add(subscription);
    }

    @Override
    public void unDisposable() {
        if(compositeDisposable!=null){
            compositeDisposable.dispose();
        }
    }
}
