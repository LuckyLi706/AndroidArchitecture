package com.jackyli.androidarchitecture.api;

import com.jackyli.androidarchitecture.model.Result;
import com.jackyli.androidarchitecture.model.UserInfo;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    //登录
    @GET("login")
    Call<Result> loginNetwork(@Query("userName") String name, @Query("passWord") String password);
}
