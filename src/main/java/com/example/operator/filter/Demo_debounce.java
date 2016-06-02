package com.example.operator.filter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by duwei on 2016/5/31.
 *
 * debounce操作符对源Observable每产生一个结果后，
 * 如果在规定的间隔时间内没有别的结果产生，
 * 则把这个结果提交给订阅者处理，否则忽略该结果。
 * 值得注意的是，如果源Observable产生的最后一个结果后
 * 在规定的时间间隔内调用了onCompleted，那么通过debounce操作符也会把这个结果提交给订阅者。
 */
public class Demo_debounce {

    public static void main(String[] args) {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if(subscriber.isUnsubscribed()) return;
                try {
                    //产生结果的间隔时间分别为100、200、300...900毫秒
                    for (int i = 1; i < 10; i++) {
                        subscriber.onNext(i);
                        Thread.sleep(i * 100);
                    }
                    subscriber.onCompleted();
                }catch(Exception e){
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.newThread())//在400毫秒内产生了1，2,3,4所以不提交给订阅者，只提交5以后的数值
                .debounce(400, TimeUnit.MILLISECONDS)  //超时时间为400毫秒
                .toBlocking()
                .subscribe(
                        new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                System.out.println("Next:" + integer);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                System.out.println("Error:" + throwable.getMessage());
                            }
                        }, new Action0() {
                            @Override
                            public void call() {
                                System.out.println("completed!");
                            }
                        });
    }


}
