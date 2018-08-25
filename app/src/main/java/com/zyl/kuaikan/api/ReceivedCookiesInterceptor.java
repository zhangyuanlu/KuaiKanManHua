package com.zyl.kuaikan.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    private static final String TAG="ReceivedCookies";
    private Context context;
    public ReceivedCookiesInterceptor(Context context){
        this.context=context;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response=chain.proceed(chain.request());
        SharedPreferences.Editor editor=context.getSharedPreferences("cookie",Context.MODE_PRIVATE).edit();
        List<String> cookieList =  response.headers("Set-Cookie");
        String cookie=null;
        if(cookieList != null) {
            for(String str:cookieList){
                 cookie=str.split(";")[0]+";";
            }
        }
        if(cookie!=null){
            editor.putString("cookie",cookie);
            editor.commit();
        }
        return response;
    }
}
