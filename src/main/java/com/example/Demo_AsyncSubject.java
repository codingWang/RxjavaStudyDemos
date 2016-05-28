package com.example;

import rx.Observer;
import rx.subjects.AsyncSubject;

/**
 * Created by 杜伟 on 2016/5/26.
 */
public class Demo_AsyncSubject {
    /**
     * һ��Ҫ����onComplete����
     * ֻ�ᷢ�����һ������
     * @param args
     */
    public static void main(String[] args){
        // observer will receive no onNext events because the subject.onCompleted() isn't called.
        AsyncSubject<Object> subject = AsyncSubject.create();
        Observer observer =  new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
               e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        subject.subscribe(observer);

        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("three");

        subject.onCompleted();//!!!!!!!!!!!!!!!!!!!!!!


        // observer will receive "three" as the only onNext event.
//        AsyncSubject<Object> subject = AsyncSubject.create();
//        subject.subscribe(observer);
//        subject.onNext("one");
//        subject.onNext("two");
//        subject.onNext("three");
//        subject.onCompleted();

    }
}
