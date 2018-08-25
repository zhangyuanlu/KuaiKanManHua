package com.zyl.kuaikan.concern;

import com.zyl.kuaikan.base.IBasePresenter;
import com.zyl.kuaikan.base.IBaseView;

public class ConcernContract {
    public interface View extends IBaseView{
        void setConcern();
    }
    public interface Presenter extends IBasePresenter{
        void loadConcern();
    }
}
