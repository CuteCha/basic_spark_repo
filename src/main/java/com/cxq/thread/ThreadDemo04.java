package com.cxq.thread;

/**
 * Created by cxq on 2017/9/28.
 */

class RunableDemo040 implements Runnable {
    private int tickets = 5;

    // 必须重新run方法
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            synchronized (this) {
                if (tickets > 0) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("tickets: " + tickets--);
                }
            }


        }
    }
}

class RunableDemo041 implements Runnable {
    private int tickets = 50;

    // 必须重新run方法
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            sell();
        }
    }

    public synchronized void sell() {
        if (tickets > 0) {
            try {
                Thread.sleep(5);
                System.out.println("tickets: " + tickets-- + "\tsold by " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadDemo04 {
    public static void main(String[] args) {
        RunableDemo041 r = new RunableDemo041();
        Thread t1 = new Thread(r, "A");
        Thread t2 = new Thread(r, "B");
        Thread t3 = new Thread(r, "C");
        t1.start();
        t2.start();
        t3.start();
    }
}
