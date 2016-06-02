package com.example.operator.creating;


import rx.functions.Action1;
import rx.functions.Func0;
import rx.util.async.Async;

/**
 * Created by duwei on 2016/5/28.
 * 创建发射一个函数的返回值的Observable
 * create an Observable that emits the return value of a function
 */
public class Demo_start {

    public static void main(String[] args) {

        Async.start(new Func0<Integer>() {
            @Override
            public Integer call() {
                return 1;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });

    }


}
