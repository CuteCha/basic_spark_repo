package com.cxq.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by cxq on 2018/9/16.
 */
public class Producer {
    private static final String FINIDHED = "EOF";
    private final RingBuffer<Event> ringBuffer;

    public Producer(RingBuffer<Event> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(String line) {
        long seq = ringBuffer.next();
        try {
            Event event = ringBuffer.get(seq);   // 获取可用位置
            event.setLine(line);                    // 填充可用位置
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ringBuffer.publish(seq);        // 通知消费者
        }
    }

    public void read(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String line;
            while ((line = reader.readLine()) != null) {
                // 生产数据
                pushData(line);
            }
            // 结束标志
            pushData(FINIDHED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
