package com.zyl.kuaikan.content;

import android.util.Log;

import com.zyl.kuaikan.api.RetrofitFactory;
import com.zyl.kuaikan.base.BasePresenterImpl;
import com.zyl.kuaikan.bean.ChapterContentBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ContentPresenter extends BasePresenterImpl<ContentContract.View> implements ContentContract.Presenter{
    private static final String TAG="ContentPresenter";
    public ContentPresenter(ContentContract.View view){
        super(view);
    }

    @Override
    public void loadChapterContent(String url) {
        RetrofitFactory.getInstance(RetrofitFactory.TYPE_GET_CHAPTERCONTENT)
                .getChapterContent(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                    }
                })
                .subscribe(new Consumer<ChapterContentBean>() {
                    @Override
                    public void accept(ChapterContentBean chapterContentBean) throws Exception {
                        view.setChapterContent(chapterContentBean);
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
