package com.zyl.kuaikan.api;

import android.util.Log;

import com.zyl.kuaikan.bean.UserBean;

import java.io.IOException;
import java.util.List;

import io.realm.Realm;
import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    private static final String TAG="ReceivedCookies";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response=chain.proceed(chain.request());
        List<String> cookieList =  response.headers("Set-Cookie");
        String cookie=null;
        if(cookieList != null) {
            for(String str:cookieList){
                 cookie=str.split(";")[0]+";";
            }
        }
        if(cookie!=null){
            Realm realm=Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.where(UserBean.class).findAll().deleteAllFromRealm();
            UserBean userBean=realm.createObject(UserBean.class);
            userBean.setCookie(cookie);
            realm.commitTransaction();
        }
        return response;
    }
}
