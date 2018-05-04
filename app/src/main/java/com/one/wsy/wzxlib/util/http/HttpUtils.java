package com.one.wsy.wzxlib.util.http;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpUtils {

    public static final String BASE_URL = "http://10.10.10.254:6000/";

    public static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;

    private TsqHttpService tsqHttpService;

    private HttpUtils() {

        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//连接 超时时间
        okhttpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//写操作 超时时间
        okhttpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//读操作 超时时间
        okhttpClientBuilder.retryOnConnectionFailure(true);//错误重连


        retrofit = new Retrofit.Builder()
                .client(okhttpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        tsqHttpService = retrofit.create(TsqHttpService.class);
    }

    private static class SingletonHolder {
        private static final HttpUtils INSTANCE = new HttpUtils();
    }

    public static HttpUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public void doGet(String url, Observer subscriber) {
        tsqHttpService.getMethod(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void doPost(String url, HashMap<String, String> params, Observer subscritber) {
        tsqHttpService.postMapMethod(url, params)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscritber);
    }
}
