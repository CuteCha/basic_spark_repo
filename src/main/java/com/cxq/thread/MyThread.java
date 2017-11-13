package com.cxq.thread;

/**
 * Created by cxq on 2017/9/27.
 */
public class MyThread extends Thread {
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    // 必须重新run方法
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(name + ":" + i);
        }
        super.run();
    }
}
