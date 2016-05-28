package com.okhttp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by duwei on 2016/5/28.
 */
public class PerCallConfiguration {

    private final OkHttpClient client = new OkHttpClient();

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://httpbin.org/delay/1") // This URL is served with a 1 second delay.
                .build();

        try {
            // Copy to customize OkHttp for this request.
            //This returns a builder that shares the same connection pool,
            // dispatcher, and configuration with the original client.
            OkHttpClient copy = client.newBuilder().readTimeout(500, TimeUnit.MILLISECONDS).build();

            System.out.println("copy1="+copy.equals(client));//false

            Response response = copy.newCall(request).execute();
            System.out.println("Response 1 succeeded: " + response);
        } catch (IOException e) {
            System.out.println("Response 1 failed: " + e);
        }

        try {
            // Copy to customize OkHttp for this request.
            OkHttpClient copy = client.newBuilder().readTimeout(3000, TimeUnit.MILLISECONDS).build();

            System.out.println("copy2="+copy.equals(client));//false

            Response response = copy.newCall(request).execute();
            System.out.println("Response 2 succeeded: " + response);
        } catch (IOException e) {
            System.out.println("Response 2 failed: " + e);
        }
    }


    public static void main(String[] args) {
        try {
            PerCallConfiguration c =  new PerCallConfiguration();
            System.out.println("client="+c.client.hashCode());
            c.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
