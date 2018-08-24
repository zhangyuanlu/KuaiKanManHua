package com.zyl.kuaikan.bean;

import java.util.List;

public class ChapterContentBean {
    private String lastUrl;
    private String nextUrl;
    private List<String> pictureList;

    public String getLastUrl() {
        return lastUrl;
    }

    public void setLastUrl(String title) {
        this.lastUrl = title;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    @Override
    public String toString() {
        return "ChapterContentBean{" +
                "lastUrl='" + lastUrl + '\'' +
                ", nextUrl='" + nextUrl + '\'' +
                ", pictureList=" + pictureList +
                '}';
    }
}
