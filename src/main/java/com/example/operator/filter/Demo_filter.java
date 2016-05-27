package com.example.operator.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * Created by 杜伟 on 2016/5/26.
 */
public class Demo_filter {


    public static void main(String[] args) {
        List<String> list = Arrays.asList("Apple","Orange","Pear","Peach","Banana","Grape","Blueberry","Lemon","Pomegranate");

        Observable.from(list)
                .filter(new Func1<String, Boolean>() {//只要条件符合filter()函数就会返回true。此时，值会发射出去并且所有的观察者都会接收到。
                    @Override
                    public Boolean call(String s) {     //filter()函数最常用的用法之一时过滤null对象：
                       // System.out.println("call"+s);
                        return s.startsWith("P");//只返回指定类型的
                    }
                }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });



    }
}
