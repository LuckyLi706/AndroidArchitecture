package com.jackyli.androidarchitecture.manager;

import com.jackyli.androidarchitecture.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : lijie
 * date : 2019/12/5 10:24
 * e-mail : jackyli706@gmail.com
 * description :
 * <p>
 * 针对大图片或者大文件，使用拦截器拦截日志的话只保留header日志
 * 不然会导致OutOfMemory异常
 * 参考解决方案  https://blog.csdn.net/jklwan/article/details/101002449
 * <p>
 * 参考：https://www.jianshu.com/p/2e8b400909b7
 */
public class RetrofitManager {

    private static Retrofit retrofit;

    //Log日志
    private final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    //Ok的拦截器
    private OkHttpClient okHttpClient() {
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        return new OkHttpClient.Builder()
                .writeTimeout(35, TimeUnit.SECONDS)
                .connectTimeout(35, TimeUnit.SECONDS)
                .readTimeout(35, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .addInterceptor(loggingInterceptor)
                //自定义连接池最大空闲连接数和等待时间大小，否则默认最大5个空闲连接
                // .connectionPool(new ConnectionPool(32, 5, TimeUnit.MINUTES))
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                // .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                // .addHeader("Accept-Encoding", "gzip, deflate")
                                .addHeader("Connection", "close")
                                // .addHeader("Accept", "*/*")
                                // .addHeader("Cookie", "add cookies here")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();

        //Retrofit的使用
    }

    private RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.HTTP_URL)
                .client(okHttpClient())
                .build();
    }

    public static Retrofit get() {
        if (retrofit == null) {
            new RetrofitManager();
        }
        return retrofit;
    }

    public <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }

}
