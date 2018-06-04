package com.cxq.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by cxq on 2018/6/4.
 */
public class LockDemo {
    public static void main(String[] args) {
        new LockDemoM().start();
        new LockDemoM().start();
        new LockDemoM().start();
    }
}

class LockDemoData{
    static int i=0;
    static Lock lock = new ReentrantLock();

    static void operate(){
        lock.lock();
        i++;
        System.out.println(i);
        lock.unlock();
    }
}


class LockDemoM extends Thread{
    public void run(){
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockDemoData.operate();
        }
    }
}