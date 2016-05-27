package com.okhttp;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.framed.Header;

/**
 * Created by 鏉滀紵 on 2016/5/27.
 */
public class SynchronousGet {
    /**请求客户端*/
    private final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {

        try {
            new SynchronousGet().run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void run() throws Exception{
        /**请求对象*/
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        /**响应对象*/
        Response response = client.newCall(request).execute();

        /**不成功*/
        if (!response.isSuccessful()) throw new IOException("Unexpected code"+response);

        /**所有的Header*/
        Headers responseHeader = response.headers();

        for (int i =0;i<responseHeader.size();i++){
            System.out.println(responseHeader.name(i)+"="+responseHeader.value(i));
        }

        System.out.println(response.body().string());//大文件避免使用string()方法，会全部加载入内存

    }
}
