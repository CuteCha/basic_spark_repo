package com.cxq.highConcurrency.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by cxq on 2018/9/4.
 */
public class FutureExa {
    static class FutureCallabe implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("do something in Callable");
            Thread.sleep(5000);
            return "finished";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new FutureCallabe());
        executorService.shutdown();
        System.out.println("do something in main");
        Thread.sleep(1000);
        String result = future.get();
        System.out.println("result: " + result);

    }
}
