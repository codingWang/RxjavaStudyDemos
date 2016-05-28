package com.okhttp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by duwei on 2016/5/27.
 */
public class TimeOut {
    private final OkHttpClient client;

    public TimeOut() throws Exception{

        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
    }


    public void run() throws Exception{

        Request request = new Request.Builder().url("http://httpbin.org/delay/2").build();

        Response response = client.newCall(request).execute();
        System.out.println("«Î«ÛÕÍ≥…"+response);

    }

    public static void main(String[] args) {
        try {
            new TimeOut().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
