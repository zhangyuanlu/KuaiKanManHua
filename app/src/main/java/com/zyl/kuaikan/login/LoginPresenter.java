package com.zyl.kuaikan.login;

import android.text.TextUtils;
import android.util.Log;

import com.zyl.kuaikan.api.RetrofitFactory;
import com.zyl.kuaikan.base.BasePresenterImpl;
import com.zyl.kuaikan.bean.LoginUserBean;
import com.zyl.kuaikan.util.Utilities;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {
    private static final String TAG="LoginPresenter";
    /**
     * 验证用户手机号与密码的合法性
     */
    public static final int WRONG_PHONENUM=100;
    public static final int WRONG_PASSWORD=200;
    public static final int LEGAL_PHONE_AND_PWD=300;
    /**
     * 尝试登录后的返回码
     */
    public static final int LOGIN_SUCCESS=200;
    public static final int LOGIN_FAILED_NEDD_CODE=14014;
    public static final int LOGIN_FAILED_EMPTY_CODE=14013;
    public static final int LOGIN_FAILED_WRONG_PWD=600004;
    public static final int LOGIN_FAILED_MANY_CODE=2037;
    public static final int LOGIN_FAILED_INVALID1_CODE=2039;
    public static final int LOGIN_FAILED_INVALID2_CODE=2040;

    public LoginPresenter(LoginContract.View view) {
        super(view);
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
    public void getVerifyCode(String phone,String type) {
        if(!Utilities.verifyPhoneNum(phone)){
            view.verifyResult(WRONG_PHONENUM);
            return;
        }
        RetrofitFactory.getInstance(RetrofitFactory.TYPE_GET_AUTO_KEYLIST)
                .getVerifyCode(phone,type)
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
                        Log.e(TAG,"result code="+user.getCode());
                        if(user.getCode()==200){
                            view.successGetCode();
                        }else{
                            view.failedGetCode();
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
