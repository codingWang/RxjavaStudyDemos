package com.okhttp;


import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostForm {

    private final OkHttpClient client = new OkHttpClient();

    public void run() throws Exception{
        RequestBody requestBody = new FormBody.Builder()
                .add("search","Jurassic Park")
                .build();

        Request request = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) throw new IOException(""+response);

        System.out.println(response.body().string());

    }

    public static void main(String[] args) {
        try {
            new PostForm().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
