package com.zyl.kuaikan.chapterList;

import com.zyl.kuaikan.base.IBasePresenter;
import com.zyl.kuaikan.base.IBaseView;
import com.zyl.kuaikan.bean.ChapterListBean;

public interface ChapterListContract {

    interface View extends IBaseView{
        /**
         * 将数据绑定到视图
         * @param bean
         */
        void setChapterList(ChapterListBean bean);
    }
    interface Presenter extends IBasePresenter{
        /**
         * 获取某作品的章节列表及相关信息
         * @param url
         */
        void loadChapterList(String url);
    }

}
