package com.cxq.highConcurrency.sync;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cxq on 2018/8/28.
 */
public class SyncInstanceMethodDemo {
    @Test
    public void test1() throws Exception {
        SyncInstanceMethodExa syncInstanceMethodExa = new SyncInstanceMethodExa();
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        executorService.execute(() -> {
            syncInstanceMethodExa.increase();
            countDownLatch.countDown();
        });
        executorService.execute(() -> {
            syncInstanceMethodExa.increase();
            countDownLatch.countDown();
        });
        countDownLatch.await();
        executorService.shutdown();
        syncInstanceMethodExa.output();
    }

    @Test
    public void test2() throws Exception {
        SyncInstanceMethodExa syncInstanceMethodExa = new SyncInstanceMethodExa();
        SyncInstanceMethodExa syncInstanceMethodExb = new SyncInstanceMethodExa();
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        executorService.execute(() -> {
            syncInstanceMethodExa.add();
            countDownLatch.countDown();
        });
        executorService.execute(() -> {
            syncInstanceMethodExb.add();
            countDownLatch.countDown();
        });
        countDownLatch.await();
        executorService.shutdown();
        syncInstanceMethodExa.output();
        syncInstanceMethodExb.output();
    }
}

class SyncInstanceMethodExa {
    static int i = 0;

    public synchronized void increase() {
        for (int j = 0; j < 10000; j++) {
            i++;
        }
    }

    public static synchronized void add() {
        for (int j = 0; j < 10000; j++) {
            i++;
        }
        System.out.println(i);
    }

    public void output() {
        System.out.println("res: "+i);
    }

}