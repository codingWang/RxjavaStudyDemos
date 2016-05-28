package com.okhttp;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by duwei on 2016/5/27.
 */
public class CancelCall {

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private final OkHttpClient client = new OkHttpClient();

    public void run() throws Exception{
        Request request=new Request.Builder()
                .url("http://httpbin.org/delay/2") // This URL is served with a 2 second delay.
                .build();

        final long startNanos = System.nanoTime();//纳秒
        final Call call = client.newCall(request);

        // Schedule a job to cancel the call in 1 second.
        executorService.schedule(new Runnable() {
            @Override public void run() {
                System.out.printf("%.2f 取消请求.%n", (System.nanoTime() - startNanos) / 1e9f);
                call.cancel();
                System.out.printf("%.2f 已取消.%n", (System.nanoTime() - startNanos) / 1e9f);
            }
        }, 1, TimeUnit.SECONDS);

        try {
            System.out.printf("%.2f 执行请求.%n", (System.nanoTime() - startNanos) / 1e9f);
            Response response = call.execute();
            System.out.printf("%.2f 要取消请求，但已经请求完了: %s%n",
                    (System.nanoTime() - startNanos) / 1e9f, response);
        } catch (IOException e) {
            System.out.printf("%.2f 取消后请求失败: %s%n",
                    (System.nanoTime() - startNanos) / 1e9f, e);
        }
    }

    public static void main(String[] args) {
        try {
            new CancelCall().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
