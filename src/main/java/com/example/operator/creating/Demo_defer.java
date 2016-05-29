package com.example.operator.creating;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func0;

/**
 * Created by duwei on 2016/5/28.
 *
 *  do not create the Observable until the observer subscribes,//ֱ������ʱ�Ŵ�������Դ
 * and create a fresh Observable for each observer//Ϊÿһ���۲��ߴ����µ�����Դ
 */
public class Demo_defer {

    public static void main(String[] args) {
        Observable<Long> observable = Observable.defer(new Func0<Observable<Long>>() {
            @Override
            public Observable call() {
                return Observable.just(System.currentTimeMillis());//���ϵͳ��ǰʱ��
            }
        });


        observable.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println(aLong);
            }
        });

        observable.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                System.out.println(aLong);
            }
        });


    }
}
