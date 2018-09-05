package com.cxq.highConcurrency.aqs;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cxq on 2018/9/3.
 */
public class CyclicBarrierExa1 {
    private final static CyclicBarrier cBarrier1 = new CyclicBarrier(5);
    private final static CyclicBarrier cBarrier2 = new CyclicBarrier(5, () -> {
        System.out.println("callback is running");
    });

    public static void main(String[] args) {
        test2();
    }

    private static void racer1(int i) throws Exception {
        Thread.sleep(1000);
        System.out.println(i + " is ready");
        cBarrier1.await();
        System.out.println(i + " continue");
    }

    private static void test1() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    racer1(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

    private static void racer2(int i) throws Exception {
        Thread.sleep(1000);
        System.out.println(i + " is ready");
        cBarrier2.await();
        System.out.println(i + " continue");
    }

    private static void test2() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    racer2(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
}
