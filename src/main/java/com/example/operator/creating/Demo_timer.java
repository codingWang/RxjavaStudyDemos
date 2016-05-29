package com.example.operator.creating;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by duewi on 2016/5/28.
 *
 * create an Observable that emits a single item after a given delay
 */
public class Demo_timer {

    public static void main(String[] args) {
        //timer by default operates on the computation Scheduler
        Observable observable = Observable.timer(5, TimeUnit.SECONDS);

        Observer observer = new Subscriber<Long>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long l) {
                System.out.println(l);          //默认输出0
            }
        };

        observable.toBlocking().subscribe(observer);//加上toBlocking

    }


}
