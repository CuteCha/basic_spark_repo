package com.cxq.thread;

/**
 * Created by cxq on 2017/9/27.
 */

class RunableDemo implements Runnable {
    private String name;

    public RunableDemo(String name) {
        this.name = name;
    }

    // 必须重新run方法
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
//            try {
//                Thread.sleep(1000);
//                System.out.println(name + ":" + i);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }


//            System.out.println(name + ":" + i);
//            System.out.println("当前线程对象: " + Thread.currentThread());
//            System.out.println("当前线程对象名称: " + Thread.currentThread().getName());
//            System.out.println();


            if(i>5){
                System.out.println(name+"礼让。。。。。。");
                Thread.yield();
            }
            System.out.println(name + ":" + i);


        }
    }
}

public class ThreadDemo02 {
    public static void main(String[] args) {
        RunableDemo r1 = new RunableDemo("A");
        RunableDemo r2 = new RunableDemo("B");
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
//        System.out.println(t1.isAlive());
        t1.start();
//        System.out.println(t1.isAlive());
        t2.start();
//        for (int i = 0; i < 50; i++) {
//            if(i>10){
//                try {
//                    t2.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println("main :" + i);
//
//        }
    }
}
