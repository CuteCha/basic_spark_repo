package com.cxq.executorservice;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by cxq on 2018/8/23.
 */

//@Slf4j
public class ExecutorDemo {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ExecutorDemo.class);

    @Test
    public void newCachedThreadPoolDemo() throws Exception{
        ExecutorService pool = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            final int index =i;
            TimeUnit.SECONDS.sleep(1);
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
        pool.shutdown();
    }

    @Test
    public void newFixedThreadPoolDemo() throws Exception{
        ExecutorService pool = Executors.newFixedThreadPool(4);
        for(int i=0;i<10;i++){
            final int index=i;
            TimeUnit.MILLISECONDS.sleep(5*(10-i));
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
        pool.shutdown();
    }

    @Test
    public void newScheduledThreadPoolDemo(){
        ScheduledExecutorService pool= Executors.newScheduledThreadPool(5);
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("running.....");
            }
        },1, 3,TimeUnit.SECONDS);
        pool.shutdown();
        pool.schedule(new Runnable() {
            @Override
            public void run() {
                //System.out.println("running.....");
                log.info("running...");
            }
        },3, TimeUnit.SECONDS);
        pool.shutdown();
    }

    @Test
    public void newSingleThreadExecutorDemo() throws Exception{
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for(int i=0;i<10;i++){
            final int index =i;
            TimeUnit.MILLISECONDS.sleep(10*i);
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
        pool.shutdown();
    }

    public static void main(String[] args) {
        //DOMConfigurator.configure("./conf/log4j.xml");
        PropertyConfigurator.configure("./conf/log4j.properties");
        log.info("ok");

    }
}
