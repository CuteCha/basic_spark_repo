package com.cxq.concurrent;

import org.apache.ivy.util.StringUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cxq on 2018/8/26.
 */
public class SyncDemo {
    private void syncTest1(String name) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println("test1- " + name + "\t" + i);
            }
            System.out.println(StringUtils.repeat("-", 36));
        }
    }

    private synchronized void syncTest2(String name) {
        for (int i = 0; i < 10; i++) {
            System.out.println("test2- " + name + "\t" + i);
        }
        System.out.println(StringUtils.repeat("-", 36));
    }

    private static void test1(){
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
    private static void test2(){
        SyncDemo syncDemo1=new SyncDemo();
        SyncDemo syncDemo2=new SyncDemo();
        ExecutorService executorService=Executors.newCachedThreadPool();
        executorService.execute(()->{
            syncDemo1.syncTest1("A");
        });
        executorService.execute(()->{
            syncDemo2.syncTest1("B");
        });
        executorService.shutdown();
    }
    public static void main(String[] args) {
        test2();
    }
}
