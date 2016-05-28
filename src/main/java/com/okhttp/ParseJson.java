package com.okhttp;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by duwei on 2016/5/27.
 */
public class ParseJson {
    /**Client*/
    private final OkHttpClient client = new OkHttpClient();
    /**Gson*/
    private final Gson gson = new Gson();

    public void run() throws Exception {
        /**Request*/
        Request request = new Request.Builder()
                .url("https://api.github.com/gists/c2a7c39532239ff261be")
                .build();
        /**Response*/
        Response response = client.newCall(request).execute();
        /**Failed*/
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        /**json to pojo*/
        Gist gist = gson.fromJson(response.body().charStream(), Gist.class);

        for (Map.Entry<String, GistFile> entry : gist.files.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().content);
        }
    }

    static class Gist {
        Map<String, GistFile> files;
    }

    static class GistFile {
        String content;
    }

}
