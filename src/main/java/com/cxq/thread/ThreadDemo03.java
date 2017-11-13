package com.cxq.thread;

/**
 * Created by cxq on 2017/9/28.
 */


class RunableDemo03 implements Runnable {
    private String name;

    public RunableDemo03(String name) {
        this.name = name;
    }

    // 必须重新run方法
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(name + ":" + i+ Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

public class ThreadDemo03 {
    public static void main(String[] args) {
        Thread t1=new Thread(new RunableDemo03("A"));
        Thread t2=new Thread(new RunableDemo03("B"));
        Thread t3=new Thread(new RunableDemo03("C"));
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        t3.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
        t3.start();

    }
}
