package com.cxq.highConcurrency.syncContainer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by cxq on 2018/8/30.
 */
public class SyncContainerExa {
    public static int threadNum = 5000;
    public static int clientNum = 200;

    /**
     * Collections.synchronizedList
     * thread safe
     */
    private static List<Integer> collSyncList = Collections.synchronizedList(Lists.newArrayList());

    @Test
    public void testSynchronizedList() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(clientNum);
        final CountDownLatch countDownLatch = new CountDownLatch(clientNum);
        for (int i = 0; i < threadNum; i++) {
            final int t = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    addCollSyncList(t);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(collSyncList.size());
    }

    private static void addCollSyncList(int i) {
        collSyncList.add(i);
    }

    /**
     * Collections.synchronizedList
     * thread safe
     */
//    private static List<Integer> vector = new Vector<>();
    private static Vector<Integer> vector = new Vector<>();

    @Test
    public void testVector() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(clientNum);
        final CountDownLatch countDownLatch = new CountDownLatch(clientNum);
        for (int i = 0; i < threadNum; i++) {
            final int t = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    addvector(t);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(vector.size());
    }

    private static void addvector(int i) {
        vector.add(i);
    }

    /**
     * Lists.newArrayList
     * thread unsafe
     */
    private static List<Integer> list = Lists.newArrayList();

    @Test
    public void testList() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(clientNum);
        final CountDownLatch countDownLatch = new CountDownLatch(clientNum);
        for (int i = 0; i < threadNum; i++) {
            final int t = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    addList(t);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(list.size());
    }

    private static void addList(int i) {
        list.add(i);
    }

    /**
     * Maps.newHashMap
     * thread unsafe
     */
    private static Map<Integer, Integer> map = Maps.newHashMap();

    @Test
    public void testHashMap() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(clientNum);
        final CountDownLatch countDownLatch = new CountDownLatch(clientNum);
        for (int i = 0; i < threadNum; i++) {
            final int t = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    updateHashMap(t);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(map.size());
    }

    private static void updateHashMap(int i) {
        map.put(i, i);
    }

    /**
     * Hashtable
     * thread safe
     */
    private static Map<Integer, Integer> hashTable = new Hashtable<>();

    @Test
    public void testHashTable() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(clientNum);
        final CountDownLatch countDownLatch = new CountDownLatch(clientNum);
        for (int i = 0; i < threadNum; i++) {
            final int t = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    updateHashTable(t);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(hashTable.size());
    }

    private static void updateHashTable(int i) {
        hashTable.put(i, i);
    }

    /**
     * Hashtable
     * thread safe
     */
    private static Map<Integer, Integer> collSyncHashMap = Collections.synchronizedMap(new HashMap<>());

    @Test
    public void testCollSyncHashMap() throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(clientNum);
        final CountDownLatch countDownLatch = new CountDownLatch(clientNum);
        for (int i = 0; i < threadNum; i++) {
            final int t = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    updateCollSyncHashMap(t);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(collSyncHashMap.size());
    }

    private static void updateCollSyncHashMap(int i) {
        collSyncHashMap.put(i, i);
    }
}
