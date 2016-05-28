package com.example.operator.filter;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;

public class Demo_skip_skipLast {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,0);

        Observable.from(list)
                .skip(2)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });


    }
}
