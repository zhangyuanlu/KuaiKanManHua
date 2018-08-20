package com.zyl.kuaikan.API;



import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {
    String BaseUrl="http://www.kuaikanmanhua.com";

    @GET("day/{index}")
    Observable<List> getPopCartoons(@Path("index")int index);
}
