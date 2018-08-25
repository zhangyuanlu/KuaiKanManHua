package com.zyl.kuaikan.base;

import io.reactivex.disposables.Disposable;

public interface IBasePresenter {
    void start();
    void detach();
    void addDisposable(Disposable subscription);
    void unDisposable();
    void tryToLogin(String phone, String pwd, String remember, String code);
}
