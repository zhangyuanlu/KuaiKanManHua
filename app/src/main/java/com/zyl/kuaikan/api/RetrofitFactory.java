package com.zyl.kuaikan.api;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static final String TAG="RetrofitFactory";
    private static final int TIMEOUT=10;
    public static final int TYPE_GET_POPLIST=100;
    public static final int TYPE_GET_CHAPTERLIST=101;
    public static final int TYPE_GET_CHAPTERCONTENT=102;
    public static final int TYPE_GET_AUTO_KEYLIST=200;
    public static final int TYPE_AUTO_LOGIN=300;

    private static OkHttpClient okHttpClient=new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e(TAG,"http msg: "+message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BASIC))
            .cookieJar(new CookieManager())
            .connectTimeout(TIMEOUT,TimeUnit.SECONDS)
            .readTimeout(TIMEOUT,TimeUnit.SECONDS)
            .build();
    private static RetrofitService retrofitService;

    public static RetrofitService getInstance(int type){
        retrofitService=null;
        switch (type){
            case TYPE_GET_CHAPTERCONTENT:
            case TYPE_GET_CHAPTERLIST:
            case TYPE_GET_POPLIST:{
                retrofitService = new Retrofit.Builder()
                        .baseUrl(RetrofitService.BaseUrl)
                        .client(okHttpClient)
                        .addConverterFactory(Html2BeanFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
                        .create(RetrofitService.class);
                break;
            }
            case TYPE_AUTO_LOGIN:
            case TYPE_GET_AUTO_KEYLIST:{
                retrofitService = new Retrofit.Builder()
                        .baseUrl(RetrofitService.BaseUrl)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
                        .create(RetrofitService.class);
                break;
            }
            default:
                break;
        }
        return retrofitService;
    }

}
