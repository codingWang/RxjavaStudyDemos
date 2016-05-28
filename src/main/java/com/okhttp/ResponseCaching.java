package com.okhttp;


import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by duwei on 2016/5/27.
 */
public class ResponseCaching {

    /**
     * 要缓存响应你必须有一个可以读写的缓存目录并限制缓存的大小
     * 多个对象操作同一目录是错的，所以请保持全局唯一的OkhttpClient实例
     * */

    private final OkHttpClient client;

    public ResponseCaching(File cacheDirectory) throws Exception{

        int cacheSize = 10*1024*1024;

        Cache cache = new Cache(cacheDirectory,cacheSize);

        client = new OkHttpClient.Builder().cache(cache).build();

    }


    public void run() throws Exception{
        Request request = new Request.Builder().url("http://publicobject.com/helloworld.txt").build();

        Response response1 = client.newCall(request).execute();

        if (!response1.isSuccessful()) throw new IOException(""+response1);

        String response1Body = response1.body().string();

        System.out.println("Response 1 response:="+response1);
        System.out.println("Response 1 cache response:="+response1.cacheResponse());//
        System.out.println("Response 1 network response:="+response1.networkResponse());


        Response response2 = client.newCall(request).execute();
        if (!response2.isSuccessful()) throw new IOException(""+response2);

        String response2Body = response2.body().string();

        System.out.println("Response 2 response:="+response2);
        System.out.println("Response 2 cache response:="+response2.cacheResponse());
        System.out.println("Response 2 network response:="+response2.networkResponse());


        System.out.println("Response 2 equals Response 1? "+response1Body.equals(response2Body));
    }

    public static void main(String[] args) {
        try {
            new ResponseCaching(new File("D:\\githubProject\\JavaProkect\\rxjava\\")).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
