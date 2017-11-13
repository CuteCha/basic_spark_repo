package com.cxq.thread;

/**
 * Created by cxq on 2017/9/27.
 */
public class ThreadDemo01 {
    public static void main(String[] args) {
        MyRunable r1 = new MyRunable("A");
        MyRunable r2 = new MyRunable("B");
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
//        MyThread t1 = new MyThread("A");
//        MyThread t2 = new MyThread("B");
//        t1.start();  // 线程启动必须通过start方法
//        t2.start();

    }
}
