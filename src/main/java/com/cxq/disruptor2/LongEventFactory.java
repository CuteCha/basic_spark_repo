package com.cxq.disruptor2;

import com.lmax.disruptor.EventFactory;

/**
 * Created by cxq on 2019-10-10 15:08
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    public LongEvent newInstance() {
        return new LongEvent();
    }

}
