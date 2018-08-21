package com.zyl.kuaikan.API;



import com.zyl.kuaikan.bean.SearchAutoComp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
    String BaseUrl="http://www.kuaikanmanhua.com";

    /**
     * 获取当日的漫画信息列表
     * @param index 当日为0，递增表示前一天
     * @return
     */
    @GET("day/{index}")
    Observable<List> getPopCartoons(@Path("index")int index);

    /**
     * 根据关键词搜索相关联的其它关键词（用于自动提示）
     * @param keyword 关键词
     * @return
     */
    @GET("web/topic/search")
    Observable<SearchAutoComp> getAboutKeywordList(@Query("keyword") String keyword);
}
