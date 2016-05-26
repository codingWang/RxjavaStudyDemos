package com.example;

import rx.Observer;
import rx.subjects.ReplaySubject;

/**
 * Created by 杜伟 on 2016/5/26.
 */
public class Demo_ReplaySubject {

    public static void main(String[] args) {

        ReplaySubject<String> subject = ReplaySubject.create();

        Observer<String> observer1 = new Observer<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                System.out.println("1="+s);
            }
        };

        Observer<String> observer2 = new Observer<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                System.out.println("2="+s);
            }
        };

        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("three");
        subject.onCompleted();

        // both of the following will get the onNext/onCompleted calls from above
        subject.subscribe(observer1);
        subject.subscribe(observer2);

    }
}
