package com.jackyli.androidarchitecture.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author : lijie
 * date : 2019/12/5 10:29
 * e-mail : jackyli706@gmail.com
 * description :
 */
public interface ApiService {

    //登录
    @GET("login")
    Observable<ResponseBody> loginNetwork(@Query("userName") String name, @Query("passWord") String password);
}
