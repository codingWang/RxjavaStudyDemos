package com.example.operator.combine;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by 鏉滀紵 on 2016/5/28.
 */
public class Demo_combineLatest {

    public static void main(String[] args) {
        //产生0,5,10,15,20数列
        Observable<Long> observable1 = Observable.interval(0,1000, TimeUnit.MILLISECONDS)//timer is Deprected
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {//0,1,2,3,4
                        return aLong*5;
                    }
                }).take(5);

        //产生0,10,20,30,40数列                     //500毫秒后开始，一秒产生一个数字
        Observable<Long> observable2 = Observable.interval(500, 1000, TimeUnit.MILLISECONDS)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {//0,1,2,3,4
                        return aLong * 10;
                    }
                }).take(5);


        Observable.combineLatest(observable1, observable2, new Func2<Long, Long, Long>() {
            @Override
            public Long call(Long aLong, Long aLong2) {
                return aLong+aLong2;
            }
        }).toBlocking()//！！！！！！！！！！！！！！
          .subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
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
