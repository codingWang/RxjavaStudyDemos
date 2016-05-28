package com.okhttp;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 杜伟 on 2016/5/27.
 */
public class AccessingHeaders {

    private final OkHttpClient client = new OkHttpClient();


    public static void main(String[] args) {
        try {
            new AccessingHeaders().run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void run() throws Exception{

        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) throw new IOException("Unexcepted code"+response);

        System.out.println("Server="+response.header("Server"));
        System.out.println("Date="+response.header("Date"));
        System.out.println("Vary="+response.headers("Vary"));
    }

}
