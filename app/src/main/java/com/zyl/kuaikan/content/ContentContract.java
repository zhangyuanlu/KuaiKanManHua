package com.zyl.kuaikan.content;

import com.zyl.kuaikan.base.IBasePresenter;
import com.zyl.kuaikan.base.IBaseView;
import com.zyl.kuaikan.bean.ChapterContentBean;

public class ContentContract {
    interface View extends IBaseView{
        void setChapterContent(ChapterContentBean bean);
    }
    interface Presenter extends IBasePresenter{
        void loadChapterContent(String url);
    }
}
