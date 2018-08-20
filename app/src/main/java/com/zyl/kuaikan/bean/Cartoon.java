package com.zyl.kuaikan.bean;

import android.graphics.Bitmap;

public class Cartoon {
    private String title;
    private String author;
    private String popIndex;
    private String briefMsg;
    private String chapterTitle;
    private String chapterPictureUrl;
    private String chapterPopIndex;
    private String chapterPublishTime;
    private String chapterContentUrl;
    private String id;

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

    public String getPopIndex() {
        return popIndex;
    }

    public void setPopIndex(String popIndex) {
        this.popIndex = popIndex;
    }

    public String getBriefMsg() {
        return briefMsg;
    }

    public void setBriefMsg(String briefMsg) {
        this.briefMsg = briefMsg;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public String getChapterPicture() {
        return chapterPictureUrl;
    }

    public void setChapterPicture(String chapterPictureUrl) {
        this.chapterPictureUrl = chapterPictureUrl;
    }

    public String getChapterPopIndex() {
        return chapterPopIndex;
    }

    public void setChapterPopIndex(String chapterPopIndex) {
        this.chapterPopIndex = chapterPopIndex;
    }

    public String getChapterPublishTime() {
        return chapterPublishTime;
    }

    public void setChapterPublishTime(String chapterPublishTime) {
        this.chapterPublishTime = chapterPublishTime;
    }

    public String getChapterContentUrl() {
        return chapterContentUrl;
    }

    public void setChapterContentUrl(String chapterContentUrl) {
        this.chapterContentUrl = chapterContentUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cartoon{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", popIndex='" + popIndex + '\'' +
                ", briefMsg='" + briefMsg + '\'' +
                ", chapterTitle='" + chapterTitle + '\'' +
                ", chapterPictureUrl='" + chapterPictureUrl + '\'' +
                ", chapterPopIndex='" + chapterPopIndex + '\'' +
                ", chapterPublishTime='" + chapterPublishTime + '\'' +
                ", chapterContentUrl='" + chapterContentUrl + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
