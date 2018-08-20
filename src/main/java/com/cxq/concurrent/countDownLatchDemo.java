package com.cxq.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by cxq on 2018/6/3.
 * 赛跑运动员准备好了后，数321所有人开始起跑
 */
public class countDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new countDownLatchDemoPerson(countDownLatch, "A").start();
        new countDownLatchDemoPerson(countDownLatch, "B").start();
        new countDownLatchDemoPerson(countDownLatch, "C").start();

        System.out.println("ready......");
        for (int i = 3; i > 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            countDownLatch.countDown();

            if (i == 1) {
                System.out.println("go");
            }
        }

    }

}

class countDownLatchDemoPerson extends Thread {
    private CountDownLatch countDownLatch;

    public countDownLatchDemoPerson(CountDownLatch countDownLatch, String name) {
        setName(name);
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            countDownLatch.await();
            for (int i = 0; i < 6; i++) {
                System.out.println(getName() + ": " + i);
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}