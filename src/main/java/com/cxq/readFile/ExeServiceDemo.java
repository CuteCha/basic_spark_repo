package com.cxq.readFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by cxq on 2018/8/21.
 */
public class ExeServiceDemo {
    public static void main(String[] args) {
        testFixedThreadPool();
        testCachedThreadPool();
        testSingleThreadPool();
        testScheduledThreadPool();
    }
    public static void testFixedThreadPool(){
        ExecutorService pool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 50; i++) {
            pool.submit(new ExeSerRunner((i + 1)));
        }
        pool.shutdown();
    }
    public static void testScheduledThreadPool(){
        ExecutorService pool = Executors.newScheduledThreadPool(4);
        for(int i = 0 ; i < 50 ; i++){
            pool.submit(new ExeSerRunner((i + 1)));
        }
        pool.shutdown();
    }
    public static void testCachedThreadPool(){
        ExecutorService pool = Executors.newCachedThreadPool();
        for(int i = 0 ; i < 50 ; i++){
            pool.submit(new ExeSerRunner((i + 1)));
        }
        pool.shutdown();
    }
    public static void testSingleThreadPool(){
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for(int i = 0 ; i < 50 ; i++){
            pool.submit(new ExeSerRunner((i + 1)));
        }
        pool.shutdown();
    }
}

class ExeSerRunner implements Runnable {

    private final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

    private Integer num;

    public ExeSerRunner(Integer num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("thread:" + Thread.currentThread().getName() + ",time:" + format.format(new Date()) + ",num:" + num);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}