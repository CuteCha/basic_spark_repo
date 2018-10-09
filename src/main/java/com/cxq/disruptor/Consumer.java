package com.cxq.disruptor;

import com.lmax.disruptor.WorkHandler;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by cxq on 2018/9/16.
 */
public class Consumer implements WorkHandler<Event> {
    private static final String FINIDHED = "EOF";
    private static AtomicInteger count = new AtomicInteger(1);
    // public static int sum=0;

//    private Consumer(){}
//    private static Consumer consumer;
//    public static Consumer getInstance(){
//        if (consumer==null){
//            synchronized (Consumer.class){
//                if (consumer==null){
//                    consumer = new Consumer();
//                }
//            }
//        }
//
//        return consumer;
//    }

    @Override
    public void onEvent(Event event) throws Exception {
        String line = event.getLine();
        if (line.equals(FINIDHED)) {
            //System.out.println("sum: "+sum);
            return;
        }
        // 消费
        line = line.trim();
        if (!StringUtils.isEmpty(line)) {
            String[] arrStr = line.split("\t");
            // int tmp = Arrays.stream(arrStr).mapToInt(x->Integer.parseInt(x)).sum();
            // sum += tmp;
            int ret = Integer.parseInt(arrStr[0]) + Integer.parseInt(arrStr[1]);
            System.out.println(String.format("L%3d\t%s + %s = %s", count.getAndIncrement(), arrStr[0], arrStr[1], ret));
        }
    }
}
