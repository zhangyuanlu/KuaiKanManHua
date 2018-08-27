package com.zyl.kuaikan.bean;

import java.util.List;

public class ChapterListBean {
    private String coverUrl;
    private String title;
    private String author;
    private String brief;
    private String heatIndex;
    private String praiseIndex;
    private String firstChapterUrl;
    private boolean isFollowed;
    private List<ChapterItem> chapterItems;
    private List<CommonCartoon> commonCartoons;

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getHeatIndex() {
        return heatIndex;
    }

    public void setHeatIndex(String heatIndex) {
        this.heatIndex = heatIndex;
    }

    public String getPraiseIndex() {
        return praiseIndex;
    }

    public void setPraiseIndex(String praiseIndex) {
        this.praiseIndex = praiseIndex;
    }

    public List<ChapterItem> getChapterItems() {
        return chapterItems;
    }

    public void setChapterItems(List<ChapterItem> chapterItems) {
        this.chapterItems = chapterItems;
    }

    public void setFirstChapterUrl(String firstChapterUrl) {
        this.firstChapterUrl = firstChapterUrl;
    }

    public String getFirstChapterUrl() {
        return firstChapterUrl;
    }

    public List<CommonCartoon> getCommonCartoons() {
        return commonCartoons;
    }

    public void setCommonCartoons(List<CommonCartoon> commonCartoons) {
        this.commonCartoons = commonCartoons;
    }

    public boolean isFollowed() {
        return isFollowed;
    }

    public void setFollowed(boolean followed) {
        isFollowed = followed;
    }

    @Override
    public String toString() {
        return "ChapterListBean{" +
                "coverUrl='" + coverUrl + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", brief='" + brief + '\'' +
                ", heatIndex='" + heatIndex + '\'' +
                ", praiseIndex='" + praiseIndex + '\'' +
                ", firstChapterUrl='" + firstChapterUrl + '\'' +
                ", isFollowed='" + isFollowed + '\'' +
                '}';
    }

    public static class ChapterItem{
        private String chapterCoverUrl;
        private String chapterTitle;
        private String chapterPraise;
        private String chapterTime;
        private String chapterUrl;

        public String getChapterCoverUrl() {
            return chapterCoverUrl;
        }

        public void setChapterCoverUrl(String chapterCoverUrl) {
            this.chapterCoverUrl = chapterCoverUrl;
        }

        public String getChapterTitle() {
            return chapterTitle;
        }

        public void setChapterTitle(String chapterTitle) {
            this.chapterTitle = chapterTitle;
        }

        public String getChapterPraise() {
            return chapterPraise;
        }

        public void setChapterPraise(String chapterPraise) {
            this.chapterPraise = chapterPraise;
        }

        public String getChapterTime() {
            return chapterTime;
        }

        public void setChapterTime(String chapterTime) {
            this.chapterTime = chapterTime;
        }

        public String getChapterUrl() {
            return chapterUrl;
        }

        public void setChapterUrl(String chapterUrl) {
            this.chapterUrl = chapterUrl;
        }

        @Override
        public String toString() {
            return "ChapterItem{" +
                    "chapterCoverUrl='" + chapterCoverUrl + '\'' +
                    ", chapterTitle='" + chapterTitle + '\'' +
                    ", chapterPraise='" + chapterPraise + '\'' +
                    ", chapterTime='" + chapterTime + '\'' +
                    ", chapterUrl='" + chapterUrl + '\'' +
                    '}';
        }
    }

    public static class CommonCartoon{
        private String coverUrl;
        private String title;
        private String brief;
        private String praiseIndex;
        private String discussIndex;
        private String url;

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getPraiseIndex() {
            return praiseIndex;
        }

        public void setPraiseIndex(String praiseIndex) {
            this.praiseIndex = praiseIndex;
        }

        public String getDiscussIndex() {
            return discussIndex;
        }

        public void setDiscussIndex(String discussIndex) {
            this.discussIndex = discussIndex;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "CommonCartoon{" +
                    "coverUrl='" + coverUrl + '\'' +
                    ", title='" + title + '\'' +
                    ", brief='" + brief + '\'' +
                    ", praiseIndex='" + praiseIndex + '\'' +
                    ", discussIndex='" + discussIndex + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
