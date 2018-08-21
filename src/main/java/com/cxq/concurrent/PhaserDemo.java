package com.cxq.concurrent;

import java.util.concurrent.Phaser;

/**
 * Created by cxq on 2018/6/3.
 * 餐厅订单处理流程
 */
public class PhaserDemo {
    public static void main(String[] args) {
        int orderNum = 5;
        Phaser phaser = new Phaser(1);

        System.out.println("start ......");

        new PhaserDemoWorker(phaser, "waiter", orderNum).start();
        new PhaserDemoWorker(phaser, "cooker", orderNum).start();
        new PhaserDemoWorker(phaser, "busboy", orderNum).start();

        for (int i = 1; i <= orderNum; i++) {
            phaser.arriveAndAwaitAdvance();
            System.out.println("order " + i + " is finished.");
        }

        phaser.arriveAndDeregister();
        System.out.println("off duty^_^");
    }
}

class PhaserDemoWorker extends Thread {
    private Phaser phaser;
    private int num;

    public PhaserDemoWorker(Phaser phaser, String name, int num) {
        this.phaser = phaser;
        setName(name);
        this.num = num;
        phaser.register();
    }

    public void run() {
        for (int i = 1; i <= num; i++) {
            System.out.println("current order is " + i + " : " + getName());
            if (i == 3) {
                phaser.arriveAndDeregister();
            } else {
                phaser.arriveAndAwaitAdvance();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
