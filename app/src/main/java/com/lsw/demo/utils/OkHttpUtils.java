package com.lsw.demo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by liushuwei on 2017/3/21.
 */

public class OkHttpUtils {
    private static final OkHttpClient okHttpClient = new OkHttpClient();


    //get方式请求网络
    /**
     * 获取Request请求对象
     * @param url
     * @return
     */
    private static Request buildRequestFromUrl(String url){
        Request request = new Request.Builder().url(url).build();
        return  request;
    }

    /**
     * 获取Response对象
     * @param url
     * @return
     * @throws IOException
     */
    private static Response buildResponseFromUrl(String url) throws IOException {
        Request request = buildRequestFromUrl(url);
        Response response = okHttpClient.newCall(request).execute();
        return response;
    }

    /**
     * 获取ResponseBody对象
     * @param url
     * @return
     * @throws IOException
     */
    private static ResponseBody buildResponseBodyFromUrl(String url) throws IOException {
        Response response = buildResponseFromUrl(url);
        if (response != null) {
            return response.body();
        }
        return null;
    }

    /**
     * 通过网络请求获取字符串
     * @param url
     * @return
     * @throws IOException
     */
    public static String getStringFromUrl(String url) throws IOException {
        ResponseBody responseBody = buildResponseBodyFromUrl(url);
        if (responseBody != null) {
            return responseBody.string();
        }
        return null;
    }

    /**
     * 通过网络请求获取字节数组
     * @param url
     * @return
     * @throws IOException
     */
    public static byte[] getByteArrayFromUrl(String url) throws IOException {
        ResponseBody responseBody = buildResponseBodyFromUrl(url);
        if (responseBody != null) {
            return responseBody.bytes();
        }
        return null;
    }

    /**
     * 通过网络请求获取数据流
     * @param url
     * @return
     * @throws IOException
     */
    public static InputStream getStreamFromUrl(String url) throws IOException {
        ResponseBody responseBody = buildResponseBodyFromUrl(url);
        if (responseBody != null) {
            return responseBody.byteStream();
        }
        return null;
    }

    /**
     * 通过异步的方式实现数据的加载
     * @param url
     */
    public static void getDataAsyn(String url, Callback callback){
        Request request = buildRequestFromUrl(url);
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * @param url
     * @param requestBody
     * @return
     */
    private static Request buildPostRequest(String url, RequestBody requestBody){
        Request.Builder builder = new Request.Builder();
        builder.url(url).post(requestBody);
        return builder.build();
    }

    /**
     * @param url
     * @param requestBody
     * @return
     * @throws IOException
     */
    public static String postRequestBody(String url, RequestBody requestBody) throws IOException {
        Request request = buildPostRequest(url, requestBody);
        Response response = okHttpClient.newCall(request).execute();
        if ((response != null) && (response.isSuccessful())) {
            return response.body().string();
        }
        return null;
    }

    /**
     * @param map
     * @return
     */
    private static RequestBody buildRequestBody(Map<String, String> map) {
        FormBody.Builder builder = new FormBody.Builder();
        if ((map != null) && (!(map.isEmpty()))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        return builder.build();
    }

    /**
     * @param url
     * @param map
     * @return
     * @throws IOException
     */
    private static String postKeyValuePair(String url,Map<String,String> map) throws IOException {
        RequestBody requestBody = buildRequestBody(map);
        return postRequestBody(url,requestBody);
    }

}
