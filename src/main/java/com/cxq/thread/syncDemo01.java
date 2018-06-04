package com.cxq.thread;

/**
 * Created by cxq on 2018/6/2.
 */
public class syncDemo01 {
    public static void main(String[] args) throws Exception {
//        syncDemoData d = new syncDemoData();
//        syncMethodDemoData d = new syncMethodDemoData();
        syncDemoDataGetLock d= new syncDemoDataGetLock();
        Thread t1 = new ThreadC(d);
        Thread t2 = new ThreadC(d);
        t1.start();
        t2.start();
    }
}
/*每个类一把锁，每个对象一把锁
 *只有获取锁的线程才能进入同步区域
 *同步块 vs 同步方法
 *1.同步区域: 同步块的粒度更小 同步方法的最小粒度是方法
 *2.获取的锁: 同步块获取的锁可以是任意对象的锁 同步方法获取的锁是当前对象的锁
 */
class syncDemoData {
    int i;

    public void process() {
        // 对象锁 同步块
        synchronized (this) {
            System.out.println("before: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            System.out.println("after: " + i);
        }
    }
}

class syncMethodDemoData {
    int i;

    // 同步方法
    public synchronized void process() {
        System.out.println("before: " + i);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i++;
        System.out.println("after: " + i);
    }
}

class syncDemoDataGetLock {
    int i;
    byte[] lock = new byte[0];

    public void process() {
        System.out.println("before: " + i);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 对象锁 同步块 获取lock
        synchronized (lock) {
            i++;
        }
        System.out.println("after: " + i);

    }
}

class DemoData {
    int i;

    public void process() {
        System.out.println("before: " + i);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i++;
        System.out.println("after: " + i);
    }
}

class ThreadC extends Thread {
    syncDemoDataGetLock d;

    public ThreadC(syncDemoDataGetLock d) {
        this.d = d;
    }

    public void run() {
        while (true) {
            d.process();
        }
    }
}