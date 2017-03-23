package com.lsw.demo;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lsw.demo.Api.ApiPostLog;
import com.lsw.demo.Api.ApiSubmitLogContent;
import com.lsw.demo.Bean.LogContentBean;
import com.lsw.demo.Bean.LogPost;

import java.util.HashMap;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private static final String HOST_SNS = "http://tracker.sns.iqiyi.com/";        // 反馈

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                submitLogContent("retrofit_test","testBug");
                postLogContent();
            }
        });
    }


    /**
     * 提交反馈信息
     */
    private void submitLogContent(final String logContent, final String bugType) {

        String QiYiVersion = "8.0";
        String userId = "reader";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("_bizType", "read_android");
        params.put("log_content", logContent);
        params.put("bugType", bugType);
        params.put("phoneBrand", Build.BRAND);
        params.put("phoneModel", Build.MODEL);
        params.put("androidVersion", Build.VERSION.RELEASE);
        params.put("qiyiVersion", QiYiVersion);
        params.put("readerVersion", "2.5.0");
        params.put("userId", userId);
        params.put("qiyiId", "123456");
        params.put("bak1", "bak1");//备用字段bak1放authCookie


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST_SNS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiSubmitLogContent api = retrofit.create(ApiSubmitLogContent.class);
        Call<LogContentBean> call = api.getLogContent(params);
        call.enqueue(new Callback<LogContentBean>() {
            @Override
            public void onResponse(Call<LogContentBean> call, retrofit2.Response<LogContentBean> response) {
                Toast.makeText(MainActivity.this,"提交成功",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LogContentBean> call, Throwable t) {
                Toast.makeText(MainActivity.this,"提交失败",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void postLogContent(){

        LogPost logPost = new LogPost();
        logPost.set_bizType("read_android");
        logPost.setAndroidVersion(Build.VERSION.RELEASE);
        logPost.setBak1("bak");
        logPost.setBugType("testBug");
        logPost.setPhoneBrand(Build.BRAND);
        logPost.setPhoneModel(Build.MODEL);
        logPost.setQiyiId("1111");
        logPost.setQiyiVersion("8.2");
        logPost.setUserId("1234");
        logPost.setLog_content("testjsonlog");
        logPost.setReaderVersion("2.5.0");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST_SNS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiPostLog api = retrofit.create(ApiPostLog.class);
        Call<LogContentBean> call = api.getPostLog(logPost);
        call.enqueue(new Callback<LogContentBean>() {
            @Override
            public void onResponse(Call<LogContentBean> call, retrofit2.Response<LogContentBean> response) {
                Toast.makeText(MainActivity.this,"提交成功",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<LogContentBean> call, Throwable t) {
                Toast.makeText(MainActivity.this,"提交失败",Toast.LENGTH_LONG).show();
            }
        });
    }

}
