package com.example.operator.transformimg;

/**
 * Created by 鏉滀紵 on 2016/5/28.
 */
public class Demo_concatMap {

    /**
     * 与flatMap操作符不同的是，concatMap操作符在处理产生的Observable时，
     * 采用的是“连接(concat)”的方式，而不是“合并(merge)”的方式，
     * 这就能保证产生结果的顺序性，也就是说提交给订阅者的结果是按照顺序提交的，不会存在交叉的情况。
     * @param args
     */
    public static void main(String[] args) {



    }
}
