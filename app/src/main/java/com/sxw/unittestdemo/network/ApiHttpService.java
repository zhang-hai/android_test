package com.sxw.unittestdemo.network;

import com.sxw.unittestdemo.mvp.bean.RefreshToken;
import com.sxw.unittestdemo.mvp.bean.UserBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by zhanghai on 2018/7/5.
 * function：
 */

public interface ApiHttpService {
    //登录
    @POST("passport/api/auth/login")
    Call<ResponseBody> requestLogin(@Body UserBean userBean);

    //登录
    @POST("passport/api/auth/login")
    Observable<RefreshToken> requestLogin2(@Body UserBean userBean);
}
