package com.okhttp;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Created by 鏉滀紵 on 2016/5/27.
 */
public class PostStreaming {

    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    private final OkHttpClient client = new OkHttpClient();


    public void run() throws Exception{
        /**请求体*/
        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_MARKDOWN;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("Numbers\n");
                sink.writeUtf8("--------\n");
                for (int i = 2; i <=997 ; i++) {
                    sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }
            }
            /**内部类里定义的方法*/
            private String factor(int n) {
                for (int i = 2; i < n; i++) {
                    int x = n/i;
                    if (x*i==n) return factor(x)+" x "+i;
                }
                return Integer.toString(n);
            }


        };

        /**请求对象包含请求体*/
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(requestBody)
                .build();

        /**执行请求*/
        Response response = client.newCall(request).execute();
        /**不成功*/
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());

    }


    public static void main(String[] args) {
        try {
            new PostStreaming().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
