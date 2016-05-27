package com.example.operator.filter;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Observer;

/**
 * Created by 杜伟 on 2016/5/26.
 */
public class Demo_takeLast {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,0);

        Observable.from(list).takeLast(3).subscribe(new Observer<Integer>() {
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
