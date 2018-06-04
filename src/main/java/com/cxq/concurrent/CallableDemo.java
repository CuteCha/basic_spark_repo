package com.cxq.concurrent;


import java.util.concurrent.*;

/**
 * Created by cxq on 2018/6/3.
 */
public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> res1 = executorService.submit(new CallableDemoSum(1, 100));
        Future<Integer> res2 = executorService.submit(new CallableDemoSum(200, 500));

        try {
            System.out.println("res1=" + res1.get() + "\nres2=" + res2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}

class CallableDemoSum implements Callable<Integer> {
    private int begin,end;

    public CallableDemoSum(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = begin; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
}
