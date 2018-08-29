package com.one.wsy.wzxlib.util.soap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ServiceStroe {

    @Headers({
            "Content-Type:text/xml;charset=utf-8",
            "SoapAction:http://tempuri.org/ADInquiry"
    })
    @POST("webServices/EhomeWebservice.asmx")
    Call<ResponseBody> getInfo(Envelope envelope);
}
