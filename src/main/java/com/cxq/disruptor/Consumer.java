package com.cxq.disruptor;

import com.lmax.disruptor.WorkHandler;
import org.apache.commons.lang.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cxq on 2018/9/16.
 */
public class Consumer implements WorkHandler<Event> {
    private static final String FINIDHED = "EOF";
    static AtomicInteger count=new AtomicInteger(0);

    @Override
    public void onEvent(Event event) throws Exception {
        String line = event.getLine();
        if (line.equals(FINIDHED)) {
            return;
        }
        // 消费
        line =line.trim();
        if(!StringUtils.isEmpty(line)) {
            String[] arrStr = line.split("\t");
            int ret = Integer.parseInt(arrStr[0]) + Integer.parseInt(arrStr[1]);
            System.out.println(String.format("L:%s\t%s", count.getAndIncrement(),ret));
        }
    }
}
