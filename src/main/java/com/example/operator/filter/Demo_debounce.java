package com.example.operator.filter;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by duwei on 2016/5/31.
 *
 * debounce��������ԴObservableÿ����һ�������
 * ����ڹ涨�ļ��ʱ����û�б�Ľ��������
 * ����������ύ�������ߴ���������Ըý����
 * ֵ��ע����ǣ����ԴObservable���������һ�������
 * �ڹ涨��ʱ�����ڵ�����onCompleted����ôͨ��debounce������Ҳ����������ύ�������ߡ�
 */
public class Demo_debounce {

    public static void main(String[] args) {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if(subscriber.isUnsubscribed()) return;
                try {
                    //��������ļ��ʱ��ֱ�Ϊ100��200��300...900����
                    for (int i = 1; i < 10; i++) {
                        subscriber.onNext(i);
                        Thread.sleep(i * 100);
                    }
                    subscriber.onCompleted();
                }catch(Exception e){
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.newThread())//��400�����ڲ�����1��2,3,4���Բ��ύ�������ߣ�ֻ�ύ5�Ժ����ֵ
                .debounce(400, TimeUnit.MILLISECONDS)  //��ʱʱ��Ϊ400����
                .toBlocking()
                .subscribe(
                        new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                System.out.println("Next:" + integer);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                System.out.println("Error:" + throwable.getMessage());
                            }
                        }, new Action0() {
                            @Override
                            public void call() {
                                System.out.println("completed!");
                            }
                        });
    }


}
