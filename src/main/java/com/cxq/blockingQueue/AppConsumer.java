package com.cxq.blockingQueue;

import org.apache.commons.lang.StringUtils;

import java.util.concurrent.BlockingQueue;

/**
 * Created by cxq on 2018/9/16.
 */
public class AppConsumer implements Runnable {
    private BlockingQueue<String> blockingQueue;
    private static final String FINIDHED = "EOF";

    public AppConsumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }


    @Override
    public void run() {
        String line;
        String[] arrStr;
        int ret;
        int count = 0;
        try {
            while (!(line = blockingQueue.take()).equals(FINIDHED)) {
                // 消费
                count++;
                line = line.trim();
                if (StringUtils.isEmpty(line)) {
                    continue;
                }
                arrStr = line.split("\t");
                ret = Integer.parseInt(arrStr[0]) + Integer.parseInt(arrStr[1]);
                System.out.println(String.format("L%3d:\t%s + %s = %s", count, arrStr[0], arrStr[1], ret));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
