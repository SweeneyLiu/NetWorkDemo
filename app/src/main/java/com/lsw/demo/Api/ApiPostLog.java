package com.lsw.demo.Api;


import com.lsw.demo.Bean.LogContentBean;
import com.lsw.demo.Bean.LogPost;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiPostLog {

    @POST("naja/log/collect_log")
    Call<LogContentBean> getPostLog(@Body LogPost logPost);
}
