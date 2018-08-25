package com.zyl.kuaikan.api;

import com.zyl.kuaikan.bean.UserBean;

import java.io.IOException;

import io.realm.Realm;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {
    private static final String TAG="AddCookiesInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder=chain.request().newBuilder();
        Realm realm=Realm.getDefaultInstance();
        realm.beginTransaction();
        final UserBean userBean=realm.where(UserBean.class).findFirst();
        realm.commitTransaction();
        if(userBean!=null) {
            builder.addHeader("Cookie", userBean.getCookie());
        }
        return chain.proceed(builder.build());
    }
}
