package com.one.wsy.wzxlib.util.http;


import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface TsqHttpService {

    @GET()
    Observable<HttpResult> getMethod(@Url String url);


    @POST()
    Observable<HttpResult> postMapMethod(@Url String url, @Body HashMap<String, String> params);

}
