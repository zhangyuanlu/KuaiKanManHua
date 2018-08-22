package com.zyl.kuaikan.api;


import com.zyl.kuaikan.bean.LoginUserBean;
import com.zyl.kuaikan.bean.UserTopicsBean;
import com.zyl.kuaikan.bean.SearchAutoComp;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
    String BaseUrl = "http://www.kuaikanmanhua.com";

    /**
     * 获取当日的漫画信息列表
     *
     * @param index 当日为0，递增表示前一天
     * @return+
     */
    @GET("day/{index}")
    Observable<List> getPopCartoons(@Path("index") int index);

    /**
     * 根据关键词搜索相关联的其它关键词（用于自动提示）
     *
     * @param keyword 关键词
     * @return
     */
    @GET("web/topic/search")
    Observable<SearchAutoComp> getAboutKeywordList(@Query("keyword") String keyword);

    /**
     * 尝试登录
     * @param userName 用户名
     * @param password 密码
     * @param remember 是否保存
     * @param code 验证码
     * @return
     */
    @POST("v1/passport/login/pc/user_mobile_login")
    Observable<LoginUserBean> tryToLogin(@Query("phone") String userName, @Query("password") String password, @Query("remember") String remember, @Query("code") String code);

    /**
     * 获取验证码
     * @param phoneNum 手机号即用户名
     * @param codeType 这里为固定值5
     * @return
     */
    @POST("v1/passport/verify_code/get_sms_code_direct")
    Observable<LoginUserBean> getVerifyCode(@Query("phone")String phoneNum, @Query("smscode_type")String codeType);

    /**
     * 获得当前用户关注列表,需要cookie
     * @return
     */
    @GET("web/fav/topics}")
    Observable<UserTopicsBean> getUserTpoics();
}
