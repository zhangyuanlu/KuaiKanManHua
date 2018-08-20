package com.zyl.kuaikan.data;

import android.support.annotation.NonNull;

import com.zyl.kuaikan.bean.PopularCartoon;

import java.util.List;
import java.util.Optional;

import io.reactivex.Flowable;

public interface CartoonDataSource {
    Flowable<List<PopularCartoon>> getPopCartoons();
    Flowable<Optional<PopularCartoon>> getCartoon(@NonNull String id);
    void refreshCartoons();
}
