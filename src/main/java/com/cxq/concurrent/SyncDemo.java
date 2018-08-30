package com.cxq.concurrent;

import org.apache.ivy.util.StringUtils;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cxq on 2018/8/26.
 */
public class SyncDemo {
    /**
     * 修饰一个代码块
     * 当前线程的锁便是实例对象syncTest2
     **/
    private void syncTest1(String name) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println("[test1] " + name + "\t" + i);
            }
            System.out.println(StringUtils.repeat("-", 36));
        }
    }

    /**
     * 修饰一个方法
     * 当前线程的锁便是实例对象syncTest2
     **/
    private synchronized void syncTest2(String name) {
        for (int i = 0; i < 10; i++) {
            System.out.println("[test2] " + name + "\t" + i);
        }
        System.out.println(StringUtils.repeat("-", 36));
    }

    // 修饰一个类
    public void syncTest3(String name) {
        synchronized (SyncDemo.class) {
            for (int i = 0; i < 10; i++) {
                System.out.println("[test3] " + name + "\t" + i);
            }
            System.out.println(StringUtils.repeat("-", 36));
        }
    }

    // 修饰一个方法
    public synchronized void syncTest4(String name) {
        for (int i = 0; i < 10; i++) {
            System.out.println("[test4] " + name + "\t" + i);
        }
        System.out.println(StringUtils.repeat("-", 36));
    }

    // 修饰一个静态方法
    public static synchronized void syncTest5(String name) {
        for (int i = 0; i < 10; i++) {
            System.out.println("[test5] " + name + "\t" + i);
        }
        System.out.println(StringUtils.repeat("-", 36));
    }


    private static void test1() {
        SyncDemo syncDemo = new SyncDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            executorService.execute(() -> {
                syncDemo.syncTest1("A");
//                syncDemo.syncTest2("A");
            });
        }
        executorService.shutdown();
    }

    private static void test2() {
        SyncDemo syncDemo1 = new SyncDemo();
        SyncDemo syncDemo2 = new SyncDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            syncDemo1.syncTest2("A");
        });
        executorService.execute(() -> {
            syncDemo2.syncTest2("B");
        });
        executorService.shutdown();
    }

    public static void test3() {
        SyncDemo syncDemo = new SyncDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            executorService.execute(() -> {
                syncDemo.syncTest3("A");
//                syncDemo.syncTest2("A");
            });
        }
        executorService.shutdown();
    }

    public static void test4() {
        SyncDemo syncDemo1 = new SyncDemo();
        SyncDemo syncDemo2 = new SyncDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            syncDemo1.syncTest4("A");
        });
        executorService.execute(() -> {
            syncDemo2.syncTest4("B");
        });
        executorService.shutdown();
    }

    public static void main(String[] args) {
//        test2();
        HashMap<String, Double> map = new HashMap<>();
        map.put("A", 5.0);
        double s = 0.0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            double t = map.get("A");
            s += 0.2 * t;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("s=" + s + "\tcost: " + (endTime - startTime));

    }
}
