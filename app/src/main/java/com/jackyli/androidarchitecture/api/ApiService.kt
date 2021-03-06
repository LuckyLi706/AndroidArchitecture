package com.jackyli.androidarchitecture.api

import com.jackyli.androidarchitecture.model.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("login")
    fun loginNetWork(@Query("userName") name: String, @Query("passWord") pass: String): Call<Result>
}