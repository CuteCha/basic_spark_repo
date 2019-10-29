package com.cxq.highConcurrency.lock;

import java.util.Arrays;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * Created by cxq on 2019-10-15 15:32
 */
public class LockDemo {
    private final Lock lock = new ReentrantLock();
    private final Condition full = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    private int count = 0;
    private int takeptr = 0;
    private int putptr = 0;
    Object[] blockArray = new Object[100];

    public void put(Object element) throws InterruptedException {
        try {
            lock.lock();
            while (count == blockArray.length) {
                System.out.println("put: putptr = " + putptr + ", await");
                full.await();       //等待和唤醒用的不是同一个对象
            }
            System.out.println("put: putptr = " + putptr + ", 执行 put");
            blockArray[putptr] = element;
            if (++putptr == blockArray.length) {
                putptr = 0;
            }
            ++count;
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        Object data = null;
        try {
            while (0 == count) {
                System.out.println("take: takeptr == " + takeptr + "，await");
                notFull.await();
            }
            System.out.println("take: takeptr = " + takeptr + ", 执行 take");
            data = blockArray[takeptr];
            if (++takeptr == blockArray.length) {
                takeptr = 0;
            }
            --count;
            full.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }

    private void conditionTest() {
        final LockDemo condition = new LockDemo();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
//                    condition.put(new Object());
//                    condition.put(7);
                    put(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
//                    condition.take();
                    take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.stream(blockArray).map(x->x.toString()).collect(Collectors.joining("\t")));
    }


    private void objLock() {
        Object lock = new Object();

        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                System.out.println("--->sum= " + sum);
                System.err.println("start sum......");
                for (int i = 0; i < 10; i++) {
                    sum += 1;
                }
                System.err.println("finish sum~~~");

//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } //阻塞必须在唤醒前

                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("<---sum=" + sum);
            }
        });

        System.out.println("A start -----");
        A.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main notify A");
        synchronized (lock) {
            lock.notify();
        }

        System.out.println("done");
    }

    private void suppLock() {
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                System.out.println("--->sum= " + sum);
                System.err.println("start sum......");
                for (int i = 0; i < 10; i++) {
                    sum += 1;
                }
                System.err.println("finish sum~~~");

//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } //阻塞可以在唤醒后，不用关心它们的顺序

                LockSupport.park();

                System.out.println("<---sum=" + sum);
            }
        });

        System.out.println("A start -----");
        A.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main notify A");

        LockSupport.unpark(A);

        System.out.println("done");
    }

    public static void main(String[] args) {
//        new LockDemo().objLock();
//        new LockDemo().suppLock();
        new LockDemo().conditionTest();
    }
}
