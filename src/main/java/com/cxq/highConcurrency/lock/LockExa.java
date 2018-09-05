package com.cxq.highConcurrency.lock;

import org.apache.hadoop.fs.shell.Count;
import org.apache.ivy.util.StringUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by cxq on 2018/9/4.
 */
public class LockExa {
    private final static int client = 5000;
    private final static int threadCount = 200;
    public static int count = 0;
    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(client);
        Semaphore semaphore = new Semaphore(threadCount);
        for (int i = 0; i < client; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(threadNum);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }

            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(StringUtils.repeat("-", 36));
        System.out.println(count);
    }

    private static void add(int i) {
        lock.lock();
        try {
            count++;
            System.out.println(i + " done");
        } finally {
            lock.unlock();
        }
    }
}
