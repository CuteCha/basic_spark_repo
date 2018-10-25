package com.cxq.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cxq on 2018/9/16.
 */
public class RunApp {
    public static void main(String[] args) {
        String fileName = "/Users/cxq/PycharmProjects/test/work/multithreads/data.txt";
        Factory factory= new Factory();  // 工厂
        ExecutorService executor = Executors.newCachedThreadPool(); // 线程池
        int ringBufferSize = 16;   // 必须为2的幂指数

        // 初始化Disruptor
        Disruptor<Event> disruptor = new Disruptor<Event>(
                factory,
                ringBufferSize,
                executor,
                ProducerType.MULTI,         // Create a RingBuffer supporting multiple event publishers to the one RingBuffer
                new YieldingWaitStrategy()  // 默认阻塞策略
        );

        // 启动消费者
         disruptor.handleEventsWithWorkerPool(new Consumer(),new Consumer()); //非单例
//        Consumer consumer = Consumer.getInstance();
//        disruptor.handleEventsWithWorkerPool(consumer,consumer);
        disruptor.start();
        // 启动生产者
        RingBuffer<Event> ringBuffer = disruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);
        producer.read(fileName);

        // 关闭
        disruptor.shutdown();
        executor.shutdown();

    }
}
