package com.cxq.singleton;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by cxq on 2018/9/13.
 */
public class SingletonTest {

    @Test
    public void testEager(){
        SingletonEager singletonEager1 = SingletonEager.getInstance();
        SingletonEager singletonEager2 = SingletonEager.getInstance();
        System.out.println(singletonEager1==singletonEager2);
        System.out.println(singletonEager1.equals(singletonEager2));
    }

    @Test
    public void testLazy(){
        SingletonLazy singletonLazy1= SingletonLazy.getInstance();
        SingletonLazy singletonLazy2= SingletonLazy.getInstance();
        System.out.println(singletonLazy1==singletonLazy2);
        System.out.println(singletonLazy1.equals(singletonLazy2));
    }

    @Test
    public void testSingletonAnno(){
        SingletonAnno singletonAnno1=SingletonAnno.getInstance();
        SingletonAnno singletonAnno2=SingletonAnno.getInstance();
        System.out.println(singletonAnno1==singletonAnno2);
        System.out.println(singletonAnno1.equals(singletonAnno2));
        singletonAnno1.showMessage(1);
        singletonAnno2.showMessage(2);
        singletonAnno1.add(0);
        singletonAnno2.add(0);

        System.out.println(StringUtils.repeat("-",36));
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(3);
        for (int i=1; i<10;i++){
            final int threadNum=i;
            if(i%2==1) {
                executorService.execute(() -> {
                    try {
                        semaphore.acquire();
                        singletonAnno1.add(threadNum);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }else {
                executorService.execute(() -> {
                    try {
                        semaphore.acquire();
                        singletonAnno2.add(threadNum);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        executorService.shutdown();
    }

}
