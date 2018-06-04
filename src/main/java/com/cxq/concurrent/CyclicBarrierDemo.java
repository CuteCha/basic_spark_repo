package com.cxq.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by cxq on 2018/6/3.
 */
public class CyclicBarrierDemo {
    public static void main(String [] args){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("game start!!!");
            }
        });

        new CyclicBarrierPlayer(cyclicBarrier,"A").start();
        new CyclicBarrierPlayer(cyclicBarrier,"B").start();
        new CyclicBarrierPlayer(cyclicBarrier,"C").start();
    }
}

class CyclicBarrierPlayer extends Thread{
    CyclicBarrier cyclicBarrier;

    public CyclicBarrierPlayer(CyclicBarrier cyclicBarrier, String name){
        setName(name);
        this.cyclicBarrier =cyclicBarrier;
    }

    public void run(){
        System.out.println(getName()+" is waiting other players ......");
        try {
            cyclicBarrier.await();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
