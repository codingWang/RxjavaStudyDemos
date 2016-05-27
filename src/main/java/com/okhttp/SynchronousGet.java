package com.okhttp;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.framed.Header;

/**
 * Created by 杜伟 on 2016/5/27.
 */
public class SynchronousGet {
    /**����ͻ���*/
    private final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {

        try {
            new SynchronousGet().run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void run() throws Exception{
        /**�������*/
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        /**��Ӧ����*/
        Response response = client.newCall(request).execute();

        /**���ɹ�*/
        if (!response.isSuccessful()) throw new IOException("Unexpected code"+response);

        /**���е�Header*/
        Headers responseHeader = response.headers();

        for (int i =0;i<responseHeader.size();i++){
            System.out.println(responseHeader.name(i)+"="+responseHeader.value(i));
        }

        System.out.println(response.body().string());//���ļ�����ʹ��string()��������ȫ���������ڴ�

    }
}
