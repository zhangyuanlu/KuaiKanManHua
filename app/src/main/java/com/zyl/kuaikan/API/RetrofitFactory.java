package com.zyl.kuaikan.API;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitFactory {
    private static final String TAG="RetrofitFactory";
    private static final int TIMEOUT=10;
    private static OkHttpClient okHttpClient=new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e(TAG,"http msg: "+message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BASIC))
            .connectTimeout(TIMEOUT,TimeUnit.SECONDS)
            .readTimeout(TIMEOUT,TimeUnit.SECONDS)
            .build();
    private static RetrofitService retrofitService= new Retrofit.Builder()
            .baseUrl(RetrofitService.BaseUrl)
            .client(okHttpClient)
            .addConverterFactory(PopCartoonsFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
            .create(RetrofitService.class);
    public static RetrofitService getInstance(){
        return retrofitService;
    }

}
