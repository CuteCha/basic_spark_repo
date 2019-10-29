package com.cxq.readFile;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by cxq on 2018/8/21.
 */
public class ExeServiceDemo {
    public static void main(String[] args) {
        testFixedThreadPool();
        testCachedThreadPool();
        testSingleThreadPool();
        testScheduledThreadPool();
        testThreadPoolExecutor();
    }

    public static void testThreadPoolExecutor() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                5,
                Runtime.getRuntime().availableProcessors() * 2,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(@NotNull Runnable r) {
                        Thread t = new Thread(r);
                        t.setName("order_thread");

                        if (t.isDaemon()) {
                            t.setDaemon(false);
                        }

                        if (t.getPriority() != Thread.NORM_PRIORITY) {
                            t.setPriority(Thread.NORM_PRIORITY);
                        }

                        return t;
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.err.println("reject " + r + "since ......");
                    }
                }

        );

        for (int i = 0; i < 50; i++) {
            pool.submit(new ExeSerRunner((i + 1)));
        }

        pool.shutdown();
    }

    public static void testFixedThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 50; i++) {
            pool.submit(new ExeSerRunner((i + 1)));
        }
        pool.shutdown();
    }

    public static void testScheduledThreadPool() {
        ExecutorService pool = Executors.newScheduledThreadPool(4);
        for (int i = 0; i < 50; i++) {
            pool.submit(new ExeSerRunner((i + 1)));
        }
        pool.shutdown();
    }

    public static void testCachedThreadPool() {
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 50; i++) {
            pool.submit(new ExeSerRunner((i + 1)));
        }
        pool.shutdown();
    }

    public static void testSingleThreadPool() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 50; i++) {
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
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}