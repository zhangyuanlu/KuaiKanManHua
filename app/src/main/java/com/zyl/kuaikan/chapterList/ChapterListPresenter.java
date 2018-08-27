package com.zyl.kuaikan.chapterList;

import android.util.Log;

import com.zyl.kuaikan.api.RetrofitFactory;
import com.zyl.kuaikan.base.BasePresenterImpl;
import com.zyl.kuaikan.bean.ChapterListBean;
import com.zyl.kuaikan.bean.ResonseBean;

import io.reactivex.Observer;
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
        Log.i(TAG,"url="+url);
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

    @Override
    public void followNow(String id) {
        RetrofitFactory.getInstance(RetrofitFactory.TYPE_FOLLOW_NOW)
                .tryToFollow(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                    }
                })
                .subscribe(new Observer<ResonseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResonseBean bean) {
                        view.followResult(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"onError");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void cancelFollow(String id) {
        RetrofitFactory.getInstance(RetrofitFactory.TYPE_FOLLOW_NOW)
                .tryCancelFollow(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        addDisposable(disposable);
                    }
                })
                .subscribe(new Observer<ResonseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResonseBean bean) {
                        view.cancelFollowResult(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"onError");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
