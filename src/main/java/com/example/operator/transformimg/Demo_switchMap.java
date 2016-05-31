package com.example.operator.transformimg;

/**
 * Created by 鏉滀紵 on 2016/5/28.
 */
public class Demo_switchMap {
    /**
     * 与flatMap操作符不同的是，switchMap操作符会保存最新的Observable产生的结果而舍弃旧的结果，
     * 举个例子来说，比如源Observable产生A、B、C三个结果，通过switchMap的自定义映射规则，
     * 映射后应该会产生A1、A2、B1、B2、C1、C2，但是在产生B2的同时，C1已经产生了，
     * 这样最后的结果就变成A1、A2、B1、C1、C2，B2被舍弃掉了！
     */
    public static void main(String[] args) {



    }

}
