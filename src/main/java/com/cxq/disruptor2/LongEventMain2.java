package com.cxq.disruptor2;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;

/**
 * Created by cxq on 2021/2/18 7:27 下午
 */
public class LongEventMain2 {
    public static void main(String[] args) throws InterruptedException {
        long beginTime = System.currentTimeMillis();

        // 生产者的线程工厂
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "simpleThread");
            }
        };

        // 指定事件工厂
        LongEventFactory factory = new LongEventFactory();

        // 指定 ring buffer字节大小，必需为2的N次方(能将求模运算转为位运算提高效率 )，否则影响性能
        int bufferSize = 1024 * 1024;

        // 单线程模式，获取额外的性能
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, threadFactory,
                ProducerType.SINGLE, new YieldingWaitStrategy());
        // 设置事件业务处理器---消费者
        disruptor.handleEventsWith(new LongEventHandler());
        // 启动disruptor线程
        disruptor.start();

        // 获取 ring buffer环，用于接取生产者生产的事件
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        // 为 ring buffer指定事件生产者
        LongEventProducer producer = new LongEventProducer(ringBuffer);

        for (int i = 0; i < 100000; i++) {
            producer.produceData(i);// 生产者生产数据
        }

        disruptor.shutdown(); //关闭 disruptor，方法会堵塞，直至所有的事件都得到处理；

        System.out.println(String.format("总共耗时%s毫秒", (System.currentTimeMillis() - beginTime)));
    }
}
