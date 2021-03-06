package com.jackyli.androidarchitecture.repository.net

import com.jackyli.androidarchitecture.BuildConfig
import com.jackyli.androidarchitecture.Constants
import com.jackyli.androidarchitecture.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitManager  {
//    private var retrofit: Retrofit? = null
//
//    companion object {
//        private var retrofit: Retrofit? = null
//
//        fun get(): Retrofit? {
//            if (retrofit == null) {
//                RetrofitManager()
//            }
//            return retrofit
//        }
//    }
//
//    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//
//    private fun okHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder().writeTimeout(5000, TimeUnit.SECONDS).readTimeout(5000, TimeUnit.SECONDS).addInterceptor(loggingInterceptor).build()
//    }
//
//
//    init {
//        retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Constants.HTTP_URL).client(okHttpClient()).build()
//    }
//
//    constructor(){
//        retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Constants.HTTP_URL).client(okHttpClient()).build()
//    }
//
//
////    fun get(): Retrofit? {
////        if (retrofit == null) {
////            RetrofitManager()
////        }
////        return retrofit
////    }
//
//    fun <T> create(clazz: Class<T>?): T {
//        return retrofit!!.create(clazz)
//    }

    private var retrofit: Retrofit? = null

    val service: ApiService by lazy { getRetrofit()!!.create(ApiService::class.java) }

    private fun getRetrofit(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(Constants.HTTP_URL)  // baseUrl
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    //.addConverterFactory(MoshiConverterFactory.create())
                   // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
        return retrofit
    }

    /**
     * 获取 OkHttpClient
     */
    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        //设置 请求的缓存的大小跟位置
        //val cacheFile = File(App.context.cacheDir, "cache")
        //val cache = Cache(cacheFile, HttpConstant.MAX_CACHE_SIZE)

        builder.run {
            addInterceptor(httpLoggingInterceptor)
            //addInterceptor(HeaderInterceptor())
            //addInterceptor(SaveCookieInterceptor())
            //addInterceptor(GsonConverterFactory.create())
            //cache(cache)  //添加缓存
            connectTimeout(5000, TimeUnit.SECONDS)
            readTimeout(5000, TimeUnit.SECONDS)
            writeTimeout(5000, TimeUnit.SECONDS)
            retryOnConnectionFailure(true) // 错误重连
            // cookieJar(CookieManager())
        }
        return builder.build()
    }
}