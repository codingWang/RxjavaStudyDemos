package com.example.operator.filter;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by 鏉滀紵 on 2016/5/28.
 */
public class Demo_ofType {

    public static void main(String[] args) {
        Observable.just(1,"Hello",true,200L,0.23f)
                .ofType(Integer.class)//按照类型对结果进行过滤
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        System.out.println(integer);
                    }
                });


    }


}
