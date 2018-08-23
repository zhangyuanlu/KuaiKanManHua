package com.zyl.kuaikan.ChapterList;

import android.util.Log;

import com.zyl.kuaikan.api.RetrofitFactory;
import com.zyl.kuaikan.base.BasePresenterImpl;
import com.zyl.kuaikan.bean.ChapterListBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ChapterListPresenter extends BasePresenterImpl<ChapterListContract.View> implements ChapterListContract.Presenter {

    public static String TAG="ChapterListPresenter";

    public ChapterListPresenter(ChapterListContract.View view){
        super(view);
    }
    @Override
    public void loadChapterList(String url) {
        Log.e(TAG,"url="+url);
        RetrofitFactory.getInstance(RetrofitFactory.TYPE_GET_CHAPTERLIST)
                .getChapterList(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                    }
                })
                .subscribe(new Consumer<ChapterListBean>() {
                    @Override
                    public void accept(ChapterListBean chapterListBean) throws Exception {
                        view.setChapterList(chapterListBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG,throwable.toString());
                        throwable.printStackTrace();
                    }
                });
    }
}
