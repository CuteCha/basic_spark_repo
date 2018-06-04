package com.cxq.thread;

import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * Created by cxq on 2018/6/2.
 */
public class ThreadDemo05 {
    public static void main(String[] args) throws IOException {
        Thread ta = new ThreadA();
        ta.start();

        Runnable r = new ThreadB();
        Thread tb = new Thread(r);
        tb.start();

        while (true) {
            System.out.println("ThreadDemo05.main");
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadA extends Thread {
    public void run() {
        while (true) {
            System.out.println("ThreadA.run: " + this.getName());
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadB implements Runnable {
    public void run() {
        while (true) {
            System.out.println("ThreadB.run");
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
