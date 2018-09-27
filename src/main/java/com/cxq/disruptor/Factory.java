package com.cxq.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by cxq on 2018/9/16.
 */
public class Factory implements EventFactory<Event> {
    @Override
    public Event newInstance() {
        return new Event();
    }
}
