package com.zyl.kuaikan.base;

/**
 * Created by zhangyuanlu on 18-8-16.
 */

public interface IBaseView<T>{
    void showloadingDialog(String msg);
    void dismissLoadingDialog();
}
