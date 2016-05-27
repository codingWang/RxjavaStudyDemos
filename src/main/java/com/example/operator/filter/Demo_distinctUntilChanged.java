package com.example.operator.filter;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Observer;

/**
 * Created by 杜伟 on 2016/5/26.
 */
public class Demo_distinctUntilChanged {


    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,1,1,1,2,2,2,3,3,4);

        Observable.from(list)
                .distinctUntilChanged()//只有数据变化的时候才发送通知
                .subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });



    }
}
