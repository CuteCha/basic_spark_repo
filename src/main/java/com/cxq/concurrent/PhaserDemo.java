package com.cxq.concurrent;

import java.util.concurrent.Phaser;

/**
 * Created by cxq on 2018/6/3.
 */
public class PhaserDemo {
    public static void main(String [] args){
        Phaser phaser = new Phaser(1);

        System.out.println("start ......");

        new PhaserDemoWorker(phaser,"waiter").start();
        new PhaserDemoWorker(phaser,"cooker").start();
        new PhaserDemoWorker(phaser,"busboy").start();

        for(int i=1;i<=3;i++){
            phaser.arriveAndAwaitAdvance();
            System.out.println("order " +i+" is finished.");
        }

        phaser.arriveAndDeregister();
        System.out.println("off duty^_^");
    }
}

class PhaserDemoWorker extends Thread{
    private Phaser phaser;
    public PhaserDemoWorker(Phaser phaser, String name){
        this.phaser=phaser;
        setName(name);
        phaser.register();
    }
    public void run(){
        for (int i=1;i<=3;i++){
            System.out.println("current order is "+i+" : "+getName());
            if(i==3){
                phaser.arriveAndDeregister();
            }else {
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
