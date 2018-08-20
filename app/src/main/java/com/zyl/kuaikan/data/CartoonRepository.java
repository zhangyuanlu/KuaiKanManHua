package com.zyl.kuaikan.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.zyl.kuaikan.bean.PopularCartoon;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.reactivex.Flowable;

public class CartoonRepository implements CartoonDataSource{
    @Nullable
    private static CartoonRepository INSTANCE=null;

    @NonNull
    private final CartoonDataSource mCartoonDataSource;

    private CartoonRepository(@NonNull CartoonDataSource dataSource){
        mCartoonDataSource=dataSource;
    }

    public static CartoonRepository getInstance(@NonNull CartoonDataSource dataSource){
        if(INSTANCE==null){
            INSTANCE=new CartoonRepository(dataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE=null;
    }

    @Override
    public Flowable<List<PopularCartoon>> getPopCartoons() {
        return null;
    }

    @Override
    public Flowable<Optional<PopularCartoon>> getCartoon(@NonNull String id) {
        return null;
    }

    @Override
    public void refreshCartoons() {

    }
}
