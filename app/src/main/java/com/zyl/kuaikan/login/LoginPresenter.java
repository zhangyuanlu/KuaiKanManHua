package com.zyl.kuaikan.login;

import android.text.TextUtils;
import android.util.Log;

import com.zyl.kuaikan.api.RetrofitFactory;
import com.zyl.kuaikan.base.BasePresenterImpl;
import com.zyl.kuaikan.bean.UserBean;
import com.zyl.kuaikan.util.Utilities;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {
    private static final String TAG="LoginPresenter";
    public static final int WRONG_PHONENUM=100;
    public static final int WRONG_PASSWORD=200;
    public static final int LEGAL_PHONE_AND_PWD=300;

    public LoginPresenter(LoginContract.View view) {
        super(view);
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
                .subscribe(new Consumer<UserBean>() {
                    @Override
                    public void accept(UserBean user) throws Exception {
                        Log.e(TAG,"result code="+user.getCode());
                        if(user.getCode()==200){
                            view.successLogin(user);
                        }else{
                            view.failedLogin(user.getCode());
                        }
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

    @Override
    public void verifyLogin(CharSequence phone, CharSequence pwd) {
        if(TextUtils.isEmpty(phone)||!Utilities.verifyPhoneNum(phone.toString())){
            view.verifyResult(WRONG_PHONENUM);
            return;
        }
        if(TextUtils.isEmpty(pwd)||!Utilities.verifyPassword(pwd.toString())){
            view.verifyResult(WRONG_PASSWORD);
            return;
        }
        view.verifyResult(LEGAL_PHONE_AND_PWD);
    }

    @Override
    public String getVerifyCode() {
        return null;
    }
}
