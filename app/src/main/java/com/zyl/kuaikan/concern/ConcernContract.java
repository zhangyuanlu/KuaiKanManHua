package com.zyl.kuaikan.concern;

import com.zyl.kuaikan.base.IBasePresenter;
import com.zyl.kuaikan.base.IBaseView;
import com.zyl.kuaikan.bean.UserTopicsBean;

public class ConcernContract {
    public interface View extends IBaseView{
        void setConcern(UserTopicsBean topicsBean);
    }
    public interface Presenter extends IBasePresenter{
        void loadConcern();
    }
}
