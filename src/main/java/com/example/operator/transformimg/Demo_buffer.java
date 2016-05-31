package com.example.operator.transformimg;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by duwei on 2016/5/29.
 *
 * periodically gather items from an Observable into bundles and
 * emit these bundles rather than emitting the items one at a time
 *
 * �����ԵĴ�����Դ��ȡ����ȥbundles����������Щbundles������һ�η�����Щitems
 *
 * buffer�����������Ե��ռ�ԴObservable�����Ľ�����б��У���������б��ύ�������ߣ�
 * �����ߴ�������buffer�б�ͬʱ������һ���ռ��Ľ�����ύ�������ߣ��ܶ���ʼ��
 * ��Ҫע����ǣ�һ��ԴObservable�ڲ�������Ĺ����г����쳣����ʹbuffer�Ѿ������ռ����Ľ����
 * ������Ҳ�������յ�����쳣���������������̡�
 */
public class Demo_buffer {

    public static void main(String[] args) {

        final String[] mails = new String[]{"Here is an email!", "Another email!", "Yet another email!"};
        //ÿ��1����������һ���ʼ�
        Observable<String> endlessMail = Observable.create(new Observable.OnSubscribe<String>() {
            @Override                           //Invoked when Observable.subscribe is called.
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if (subscriber.isUnsubscribed()) return;
                    Random random = new Random();
                    while (true) {
                        String mail = mails[random.nextInt(mails.length)];
                        subscriber.onNext(mail);
                        Thread.sleep(1000);
                    }
                } catch (Exception ex) {
                    subscriber.onError(ex);
                }
            }
        }).subscribeOn(Schedulers.io());
        //������������ʼ����ݻ��浽�б��У���ÿ��3��֪ͨ������
        endlessMail.buffer(3, TimeUnit.SECONDS).toBlocking().subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> list) {

                System.out.println(String.format("You've got %d new messages!  Here they are!", list.size()));
                for (int i = 0; i < list.size(); i++)
                    System.out.println("**" + list.get(i).toString());
            }
        });

    }

}
