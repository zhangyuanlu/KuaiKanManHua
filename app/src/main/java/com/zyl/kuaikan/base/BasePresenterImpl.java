package com.zyl.kuaikan.base;

import android.util.Log;

import com.zyl.kuaikan.api.RetrofitFactory;
import com.zyl.kuaikan.bean.LoginUserBean;
import com.zyl.kuaikan.login.LoginPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenterImpl<V extends IBaseView> implements IBasePresenter {
    private static final String TAG="BasePresenterImpl";
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
    @Override
    public void tryToLogin(String phone, String pwd, String remember, String code) {
        RetrofitFactory.getInstance(RetrofitFactory.TYPE_GET_AUTO_KEYLIST)
                .tryToLogin(phone,pwd,remember,code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                    }
                })
                .subscribe(new Consumer<LoginUserBean>() {
                    @Override
                    public void accept(LoginUserBean user) throws Exception {
                        if(user.getCode()== LoginPresenter.LOGIN_SUCCESS){
                            view.successLogin(user);
                        }else{
                            view.failedLogin(user.getCode());
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
