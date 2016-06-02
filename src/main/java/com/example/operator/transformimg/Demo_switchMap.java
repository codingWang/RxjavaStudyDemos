package com.example.operator.transformimg;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by 鏉滀紵 on 2016/5/28.
 */
public class Demo_switchMap {
    /**
     * 与flatMap操作符不同的是，switchMap操作符会保存最新的Observable产生的结果而舍弃旧的结果，
     * 举个例子来说，比如源Observable产生A、B、C三个结果，通过switchMap的自定义映射规则，
     * 映射后应该会产生A1、A2、B1、B2、C1、C2，但是在产生B2的同时，C1已经产生了，
     * 这样最后的结果就变成A1、A2、B1、C1、C2，B2被舍弃掉了！
     */
    public static void main(String[] args) {
        Observable.just(10, 20, 30).switchMap(new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                //10的延迟执行时间为200毫秒、20和30的延迟执行时间为180毫秒
                int delay = 200;
                if (integer > 10)
                    delay = 180;
                //只有最后一个（30,15）打印出来了，因为最后一个替换了前面的过期的Observable（10和20）
                return Observable.from(new Integer[]{integer, integer / 2}).delay(delay, TimeUnit.MILLISECONDS);
            }
        }).toBlocking().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("switchMap Next:" + integer);
            }
        });


    }

}
