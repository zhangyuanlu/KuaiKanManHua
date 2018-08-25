package com.zyl.kuaikan.base;

import com.zyl.kuaikan.bean.LoginUserBean;

/**
 * Created by zhangyuanlu on 18-8-16.
 */

public interface IBaseView<T>{
    void showToastMsg(String msg);
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
}
