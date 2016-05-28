package com.example;

import rx.Observer;
import rx.Subscription;
import rx.subjects.PublishSubject;

/**
 * Created by duwei on 2016/5/26.
 */
public class Demo_PublishSubject {


    public static void main(String[] args){
        //没有数据要发送，因此我们的观察者只能等待，没有阻塞线程，也没有消耗资源

        rx.subjects.PublishSubject<String> stringPublishSubject = rx.subjects.PublishSubject.create();

        Subscription subscription = stringPublishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });


        stringPublishSubject.onNext("Hello Word");

    }
}
