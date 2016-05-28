package com.okhttp;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by duwei on 2016/5/28.
 * 发送请求需要身份验证时
 */
public class Authentication {
    private final OkHttpClient client;

    public Authentication() {
        client = new OkHttpClient.Builder()
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        System.out.println("Authenticating for response: " + response);

                        System.out.println("Challenges: " + response.challenges());
                        String credential = Credentials.basic("jesse", "password1");
                        return response.request().newBuilder()
                                .header("Authorization", credential)
                                .build();
                    }
                }).build();
    }

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://publicobject.com/secrets/hellosecret.txt")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());
    }

    public static void main(String[] args) {
        try {
            new Authentication().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
