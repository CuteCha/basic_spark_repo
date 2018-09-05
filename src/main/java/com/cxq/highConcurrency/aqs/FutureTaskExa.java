package com.cxq.highConcurrency.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by cxq on 2018/9/4.
 */
public class FutureTaskExa {
    public static void main(String[] args) throws Exception{
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("do something in callable");
                Thread.sleep(5000);
                return "finished";
            }
        });

        new Thread(futureTask).start();
        System.out.println("do something in main");
        Thread.sleep(1000);
        String result = futureTask.get();
        System.out.println("result: "+ result);
    }
}
