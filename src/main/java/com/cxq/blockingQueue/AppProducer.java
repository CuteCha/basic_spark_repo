package com.cxq.blockingQueue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.BlockingQueue;

/**
 * Created by cxq on 2018/9/16.
 */
public class AppProducer implements Runnable {
    private BlockingQueue<String> blockingQueue;
    private String fileName;
    private static final String FINIDHED = "EOF";

    public AppProducer(BlockingQueue<String> blockingQueue, String fileName)  {
        this.blockingQueue = blockingQueue;
        this.fileName = fileName;
    }


    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            String line;
            while ((line = reader.readLine()) != null) {
                blockingQueue.put(line);
            }
            blockingQueue.put(FINIDHED); //结束标志
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
