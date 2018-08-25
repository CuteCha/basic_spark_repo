package com.cxq.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by cxq on 2018/6/4.
 */
public class ForkJoinDemo {
    public static void main(String[] args) throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Long> res = forkJoinPool.submit(new ForkJoinDemoM(1, 1000000));

        System.out.println("res: " + res);
        forkJoinPool.shutdown();
    }
}


class ForkJoinDemoM extends RecursiveTask<Long> {
    static final int THRESHOLD = 1000;

    private int begin, end;

    public ForkJoinDemoM(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if ((end - begin) <= THRESHOLD) {
            for (int i = begin; i <= end; i++) {
                sum += i;
            }
        } else {
            int mid = (begin + end) / 2;
            ForkJoinDemoM leftM = new ForkJoinDemoM(begin, mid);
            leftM.fork();
            ForkJoinDemoM rightM = new ForkJoinDemoM(mid + 1, end);
            rightM.fork();

            Long leftRes = leftM.join();
            System.out.println(begin + "~" + mid + ": " + leftRes);
            Long rightRes = rightM.join();
            System.out.println((mid + 1) + "~" + end + ": " + rightRes);
            sum = leftRes + rightRes;

        }
        return sum;
    }
}