package com.cxq.disruptor2;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * Created by cxq on 2019-10-10 15:09
 */
public class LongEventProducerWithTranslator {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void produceData(long value) {
        ringBuffer.publishEvent(TRANSLATOR, value);
    }

    // 使用EventTranslator, 封装获取Event的过程
    private static final EventTranslatorOneArg<LongEvent, Long> TRANSLATOR = new EventTranslatorOneArg<LongEvent, Long>() {
        @Override
        public void translateTo(LongEvent event, long sequeue, Long value) {
            event.setValue(value);
        }

    };

}
