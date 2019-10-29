package com.cxq.disruptor2;

import java.io.Serializable;

/**
 * Created by cxq on 2019-10-10 15:07
 */
@SuppressWarnings("serial")
public class LongEvent implements Serializable {
    private long value;

    public LongEvent() {
        super();
    }

    public LongEvent(long value) {
        super();
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LongEvent [value=" + value + "]";
    }


}
