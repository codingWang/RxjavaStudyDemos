package com.example.operator.creating;

import rx.Observable;
import rx.Observer;

/**
 * Created by 杜伟 on 2016/5/28.
 *
 * create an Observable that emits a range of sequential integers
 */
public class Demo_range {

    public static void main(String[] args) {

        Observable observable  = Observable.range(5,10);

        Observer observer = new Observer() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Object o) {
                System.out.println(o);
            }
        };

        observable.subscribe(observer);

    }
}
