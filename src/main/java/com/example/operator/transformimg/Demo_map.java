package com.example.operator.transformimg;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by 杜伟 on 2016/5/28.
 */
public class Demo_map {

    public static void main(String[] args) {
        Observable.just(1,2,3,4,5)
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return 10*integer;
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });


    }


}
