package com.zyl.kuaikan.login;

import com.zyl.kuaikan.base.IBasePresenter;
import com.zyl.kuaikan.base.IBaseView;
import com.zyl.kuaikan.bean.LoginUserBean;

public interface LoginContract {
    interface View extends IBaseView{
        /**
         * 登录成功
         * @param user 登录用户
         */
        void successLogin(LoginUserBean user);

        /**
         * 登录失败
         * @return
         */
        void failedLogin(int resultCode);

        /**
         * 校验用户名与密码是否合法
         * @param resultCode
         */
        void verifyResult(int resultCode);

        /**
         * 成功获取验证码
         */
        void successGetCode();

        /**
         * 获取验证码失败
         */
        void failedGetCode();

    }
    interface Presenter extends IBasePresenter{
        /**
         * 尝试登录
         * @param phone 手机号
         * @param pwd 密码
         * @param remember 是否记住
         * @param code 验证码
         * @return
         */
        void tryToLogin(String phone, String pwd, String remember, String code);

        /**
         * 校验用户名和密码的合法性
         * @param phone 手机号
         * @param pwd 密码
         */
        void verifyLogin(CharSequence phone,CharSequence pwd);

        /**
         * 获得验证码
         * @return
         */
        void getVerifyCode(String phone,String type);
    }
}
