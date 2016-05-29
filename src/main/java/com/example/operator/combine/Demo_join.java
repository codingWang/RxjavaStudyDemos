package com.example.operator.combine;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by 鏉滀紵 on 2016/5/28.
 */
public class Demo_join {
    public static void main(String[] args) {
        //0,5,10,15,20
        Observable<Long> observable1 = Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 5;
                    }
                }).take(5);

        //0,10,20,30,40
        Observable<Long> observable2 = Observable.interval(500, 1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        return aLong * 10;
                    }
                }).take(5);
        //observableA.join(observableB,
        //  observableA产生结果生命周期控制函数，
        //  observableB产生结果生命周期控制函数，
        //   observableA产生的结果与observableB产生的结果的合并规则）
        observable1.join(observable2, new Func1<Long, Observable<Long>>() {
            @Override
            public Observable<Long> call(Long aLong) {
                return Observable.just(aLong).delay(600, TimeUnit.MILLISECONDS);
            }
        }, new Func1<Long, Observable<Long>>() {
            @Override
            public Observable<Long> call(Long aLong) {
                return Observable.just(aLong).delay(600, TimeUnit.MILLISECONDS);
            }
        }, new Func2<Long, Long, Long>() {
            @Override
            public Long call(Long aLong, Long aLong2) {
                return aLong+aLong2;
            }
        }).toBlocking().subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("complete" );
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                System.out.println(aLong);
            }
        });
    }


}
