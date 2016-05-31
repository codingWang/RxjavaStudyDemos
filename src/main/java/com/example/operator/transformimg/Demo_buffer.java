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
 * 周期性的从数据源获取数据去bundles，并发送这些bundles而不是一次发射这些items
 *
 * buffer操作符周期性地收集源Observable产生的结果到列表中，并把这个列表提交给订阅者，
 * 订阅者处理后，清空buffer列表，同时接收下一次收集的结果并提交给订阅者，周而复始。
 * 需要注意的是，一旦源Observable在产生结果的过程中出现异常，即使buffer已经存在收集到的结果，
 * 订阅者也会马上收到这个异常，并结束整个过程。
 */
public class Demo_buffer {

    public static void main(String[] args) {

        final String[] mails = new String[]{"Here is an email!", "Another email!", "Yet another email!"};
        //每隔1秒就随机发布一封邮件
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
        //把上面产生的邮件内容缓存到列表中，并每隔3秒通知订阅者
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
