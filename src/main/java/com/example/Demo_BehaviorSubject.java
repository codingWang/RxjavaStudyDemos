package com.example;

import rx.Observer;
import rx.subjects.BehaviorSubject;

/**
 * Created by 杜伟 on 2016/5/26.
 */
public class Demo_BehaviorSubject {


    public static void main(String[] args) {

        // observer will receive all events.
        BehaviorSubject<String> subject = BehaviorSubject.create("default");

        Observer observer =  new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        subject.subscribe(observer);//收到后面 三个事件
        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("three");

//        // observer will receive the "one", "two" and "three" events, but not "zero"
//        BehaviorSubject<Object> subject = BehaviorSubject.create("default");
//        subject.onNext("zero");
//        subject.onNext("one");        //最近一个事件
//        subject.subscribe(observer);
//        subject.onNext("two");
//        subject.onNext("three");
//
//        // observer will receive only onCompleted
//        BehaviorSubject<Object> subject = BehaviorSubject.create("default");
//        subject.onNext("zero");
//        subject.onNext("one");
//        subject.onCompleted();
//        subject.subscribe(observer);//只收到最近一个事件
//
//        // observer will receive only onError
//        BehaviorSubject<Object> subject = BehaviorSubject.create("default");
//        subject.onNext("zero");
//        subject.onNext("one");
//        subject.onError(new RuntimeException("error"));//只收到error
//        subject.subscribe(observer);


    }
}
