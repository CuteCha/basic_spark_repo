package com.cxq.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by cxq on 2018/9/16.
 */
public class RunApp {
    public static void main(String[] args) {
        String fileName = "/Users/cxq/PycharmProjects/test/work/multithreads/data.txt";
        // 初始化阻塞队列
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1000);
        // 创建生产者线程
        Thread producer = new Thread(new AppProducer(blockingQueue, fileName));
        producer.start();
        // 创建消费者线程
        Thread consumer = new Thread(new AppConsumer(blockingQueue));
        consumer.start();
    }
}
