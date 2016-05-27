package com.okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by duwei on 2016/5/27.
 * ע�⣬�ص����������߳�
 */
public class AsynchronousGet {
    /**����ͻ���*/
    private final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {

        try {
            System.out.println("�����߳���"+Thread.currentThread().getName());
            new AsynchronousGet().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() throws Exception{
        /**�������*/
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        /**������Ӳ��ص�*/
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexcepted code"+response);

                Headers headers = response.headers();

                for (int i = 0; i < headers.size(); i++) {
                    System.out.println(headers.name(i)+"="+headers.value(i));
                }

                System.out.println(response.body().string());

                System.out.println("�ص��߳���"+Thread.currentThread().getName());
            }
        });

    }

}
