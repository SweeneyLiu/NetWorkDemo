package com.lsw.demo.Api;



import com.lsw.demo.Bean.LogContentBean;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiSubmitLogContent {

    @POST("naja/log/collect_log")
    Call<LogContentBean> getLogContent(@Body HashMap<String, String> hashMap);
}
