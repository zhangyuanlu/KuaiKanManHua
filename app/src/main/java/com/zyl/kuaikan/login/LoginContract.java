package com.zyl.kuaikan.login;

import com.zyl.kuaikan.base.IBasePresenter;
import com.zyl.kuaikan.base.IBaseView;
import com.zyl.kuaikan.bean.UserBean;

public interface LoginContract {
    interface View extends IBaseView{
        /**
         * 登录成功
         * @param user 登录用户
         */
        void successLogin(UserBean user);

        /**
         * 登录失败
         * @return
         */
        void failedLogin(int resultCode);

        /**
         * 校验结果
         * @param resultCode
         */
        void verifyResult(int resultCode);
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
        String getVerifyCode();
    }
}
