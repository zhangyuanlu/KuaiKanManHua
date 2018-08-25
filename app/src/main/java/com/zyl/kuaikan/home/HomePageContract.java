package com.zyl.kuaikan.home;

import com.zyl.kuaikan.base.IBasePresenter;
import com.zyl.kuaikan.base.IBaseView;
import com.zyl.kuaikan.bean.LoginUserBean;
import com.zyl.kuaikan.bean.PopularCartoon;
import com.zyl.kuaikan.bean.SearchAutoComp;

import java.util.List;

/**
 * Created by zhangyuanlu on 18-8-16.
 */

public interface HomePageContract {
    interface View extends IBaseView{

        /**
         * 设置每日的漫画信息
         * @param popularCartoons 漫画列表
         */
        void setDayPops(List<PopularCartoon> popularCartoons);

        /**
         * 根据关键词设置自动补全的提示
         * @param searchAutoComp 关键词
         */
        void setSearchAutoComp(SearchAutoComp searchAutoComp);
    }
    interface Presenter extends IBasePresenter{
        /**
         * 获取每日的漫画信息
         * @param forceUpdate 是否强制刷新
         * @param index 当日为0，递增到6
         */
        void loadPopCartoons(boolean forceUpdate,int index);

        /**
         * 根据关键词查询是否有该作品
         * @param keyWord
         */
        void getAutoCompBindList(String keyWord);

    }
}
