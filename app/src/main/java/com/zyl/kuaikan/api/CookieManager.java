package com.zyl.kuaikan.api;

import com.zyl.kuaikan.applicaiton.MyApplication;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class CookieManager implements CookieJar {
    private final PersistentCookieStore cookieStore=new PersistentCookieStore(MyApplication.getApp());

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        if(cookies!=null&&cookies.size()>0){
            for(Cookie cookie:cookies){
                cookieStore.add(url,cookie);
            }
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies=cookieStore.get(url);
        return cookies;
    }
}
