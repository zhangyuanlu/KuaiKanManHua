package com.zyl.kuaikan.bean;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by zhangyuanlu on 18-8-17.
 */

public class PopularCartoon {
    private String bitmapUrl;
    private String popIndex;
    private String title;
    private String author;
    private String urlDetail;
    private String id;

    public String getBitmapUrl() {
        return bitmapUrl;
    }

    public void setBitmapUrl(String bitmapUrl) {
        this.bitmapUrl = bitmapUrl;
    }

    public String getPopIndex() {
        return popIndex;
    }

    public void setPopIndex(String popIndex) {
        this.popIndex = popIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrlDetail() {
        return urlDetail;
    }

    public void setUrlDetail(String urlDetail) {
        this.urlDetail = urlDetail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PopularCartoon{" +
                "bitmapUrl='" + bitmapUrl + '\'' +
                ", popIndex='" + popIndex + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", urlDetail='" + urlDetail + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
