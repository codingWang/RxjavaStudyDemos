package com.example;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by 杜伟 on 2016/5/26.
 */
public class Demo_HelloWord {

    public static void main(String[] args){

        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>(){//extends Action1

                @Override
                public void call(Subscriber<? super String> subscriber) {
                    subscriber.onNext("Hello Word!");
                    subscriber.onCompleted();
                }
            });

        Subscriber<String> mySubscriber = new Subscriber<String>() {//implement Observer&&Subscription
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
        };


        myObservable.subscribe(mySubscriber);

    }
}
