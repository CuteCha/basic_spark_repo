package com.cxq.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by cxq on 2018/8/26.
 */
public class ConcurrencyTest {
    public static int clientNum=5000;
    public static int threadNum=50;
    public static int count=0;
    public static void main(String[] args) throws Exception {
        ExecutorService executorService= Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadNum);
        final CountDownLatch countDownLatch = new CountDownLatch(clientNum);

        for(int i=0;i<clientNum;i++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("count: "+count);

    }
    private static void add0(){
        count++;
    }
    private static synchronized void add(){
        count++;
    }
}
