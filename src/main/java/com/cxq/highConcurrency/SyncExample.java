package com.cxq.highConcurrency;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cxq on 2018/8/28.
 */
public class SyncExample {
    @Test
    public void testSyncUtil1() {
        SyncUtilsDemo syncUtilsDemo = new SyncUtilsDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            syncUtilsDemo.syncUtil1("A");
        });
        executorService.execute(() -> {
            syncUtilsDemo.syncUtil1("B");
        });
        executorService.shutdown();
    }

    @Test
    public void testSyncUtil1_0() {
        SyncUtilsDemo syncUtilsDemo1 = new SyncUtilsDemo();
        SyncUtilsDemo syncUtilsDemo2 = new SyncUtilsDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            syncUtilsDemo1.syncUtil1("A");
        });
        executorService.execute(() -> {
            syncUtilsDemo2.syncUtil1("B");
        });
        executorService.shutdown();
    }

    @Test
    public void testSyncUtil2() {
        SyncUtilsDemo syncUtilsDemo = new SyncUtilsDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            syncUtilsDemo.syncUtil2("A");
        });
        executorService.execute(() -> {
            syncUtilsDemo.syncUtil2("B");
        });
        executorService.shutdown();
    }

    @Test
    public void testSyncUtil2_0() {
        SyncUtilsDemo syncUtilsDemo1 = new SyncUtilsDemo();
        SyncUtilsDemo syncUtilsDemo2 = new SyncUtilsDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            syncUtilsDemo1.syncUtil2("A");
        });
        executorService.execute(() -> {
            syncUtilsDemo2.syncUtil2("B");
        });
        executorService.shutdown();
    }

    @Test
    public void testSyncUtil3() {
        SyncUtilsDemo syncUtilsDemo = new SyncUtilsDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            syncUtilsDemo.syncUtil3("A");
        });
        executorService.execute(() -> {
            syncUtilsDemo.syncUtil3("B");
        });
        executorService.shutdown();
    }

    @Test
    public void testSyncUtil3_0() {
        SyncUtilsDemo syncUtilsDemo1 = new SyncUtilsDemo();
        SyncUtilsDemo syncUtilsDemo2 = new SyncUtilsDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            syncUtilsDemo1.syncUtil3("A");
        });
        executorService.execute(() -> {
            syncUtilsDemo2.syncUtil3("B");
        });
        executorService.shutdown();
    }

}

class SyncUtilsDemo {
    public void syncUtil1(String name) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println("syncUtil1-" + name + ": " + i);
            }
        }
    }

    public synchronized void syncUtil2(String name) {
        for (int i = 0; i < 10; i++) {
            System.out.println("syncUtil2-" + name + ": " + i);
        }
    }

    public void syncUtil3(String name) {
        synchronized (SyncUtilsDemo.class) {
            for (int i = 0; i < 10; i++) {
                System.out.println("syncUtil3-" + name + ": " + i);
            }
        }
    }

    public static synchronized void syncUtil4(String name) {
        for (int i = 0; i < 10; i++) {
            System.out.println("syncUtil4-" + name + ": " + i);
        }
    }
}