package com.zyl.kuaikan.home;

import android.database.Observable;
import android.graphics.Bitmap;

import com.zyl.kuaikan.base.IBasePresenter;
import com.zyl.kuaikan.base.IBaseView;
import com.zyl.kuaikan.bean.PopularCartoon;

import java.util.List;

import okhttp3.ResponseBody;

/**
 * Created by zhangyuanlu on 18-8-16.
 */

public interface HomePageContract {
    interface View extends IBaseView{
        void setCarouseList(List<Bitmap> bitmaps);
        void setDayPops(List<PopularCartoon> popularCartoons);
    }
    interface Presenter extends IBasePresenter{
        void loadPopCartoons(boolean forceUpdate,int index);
    }
}
