package com.example.operator.filter;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Observer;

/**
 * Created by 杜伟 on 2016/5/26.
 */
public class Demo_first_last {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Apple","Orange","Pear","Peach","Banana","Grape","Blueberry","Lemon","Pomegranate");

        Observable.from(list)
                .first()                        //只对第一个数据感兴趣    //.last()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
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
