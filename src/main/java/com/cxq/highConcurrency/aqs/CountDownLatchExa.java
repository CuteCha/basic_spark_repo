package com.cxq.highConcurrency.aqs;

import org.apache.ivy.util.StringUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cxq on 2018/9/6.
 */
public class CountDownLatchExa {
    private final static int threadCount = 20;
    private static int count = 0;

    public static void test() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    add(threadNum);
                } catch (Exception e) {
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

    private static synchronized void add(int i) throws Exception {
        Thread.sleep(1000);
        count++;
        System.out.println(i + " start: " + count);
//        Thread.sleep(1000);
        System.out.println(i + " finished: " + count);
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}
