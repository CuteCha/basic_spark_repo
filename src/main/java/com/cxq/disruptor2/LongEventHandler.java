package com.cxq.disruptor2;

import com.lmax.disruptor.EventHandler;

/**
 * Created by cxq on 2019-10-10 15:10
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("consumer:" + Thread.currentThread().getName() + " Event: value=" + event.getValue() + ",sequence=" + sequence + ",endOfBatch=" + endOfBatch);
    }

}
