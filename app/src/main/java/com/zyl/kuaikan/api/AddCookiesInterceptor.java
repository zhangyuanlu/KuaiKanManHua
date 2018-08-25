package com.zyl.kuaikan.api;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {
    private static final String TAG="AddCookiesInterceptor";
    private Context context;
    public AddCookiesInterceptor(Context context){
        this.context=context;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder=chain.request().newBuilder();
        SharedPreferences sharedPreferences=context.getSharedPreferences("cookie",Context.MODE_PRIVATE);
        String cookie=sharedPreferences.getString("cookie","");
        builder.addHeader("Cookie",cookie);
        return chain.proceed(builder.build());
    }
}
