package com.lsw.demo.Api;


import com.lsw.demo.Bean.LogContentBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface ApiSubmitGzipLogContent {

    @POST("naja/log/collect_log")
    Call<ResponseBody> getGzipLogContent(@Body String content);
}
