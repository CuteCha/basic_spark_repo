package com.cxq.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cxq on 2018/6/4.
 */
public class AtomDemo {
    public static void main(String[] args) {
        new AtomDemoM().start();
        new AtomDemoM().start();
        new AtomDemoM().start();
    }
}

class AtomDemoData{
    static AtomicInteger atomicInteger = new AtomicInteger(0);

    static void operate(){
        System.out.println(atomicInteger.getAndIncrement());
    }
}


class AtomDemoM extends Thread{
    public void run(){
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            AtomDemoData.operate();
        }
    }
}