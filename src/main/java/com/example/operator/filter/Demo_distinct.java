package com.example.operator.filter;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Observer;

/**
 * Created by 杜伟 on 2016/5/26.
 */
public class Demo_distinct {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,1,2,3,4,5);

        Observable.from(list)
                .distinct().subscribe(new Observer<Integer>() {
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
