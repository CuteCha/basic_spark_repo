package com.cxq.concurrent;

import java.util.concurrent.Semaphore;

/**
 * Created by cxq on 2018/6/3.
 * 银行柜台有2个服务员，有3个人排队等待服务
 */
public class SemaphoreDemo {
    public static void main(String [] args){
        Semaphore semaphore = new Semaphore(2);
        SemaphoreDemoPerson p1= new SemaphoreDemoPerson(semaphore,"A");
        p1.start();
        SemaphoreDemoPerson p2= new SemaphoreDemoPerson(semaphore,"B");
        p2.start();
        SemaphoreDemoPerson p3= new SemaphoreDemoPerson(semaphore,"C");
        p3.start();
    }
}

class SemaphoreDemoPerson extends Thread{
    private Semaphore semaphore;

    public SemaphoreDemoPerson(Semaphore semaphore, String name){
        setName(name);
        this.semaphore = semaphore;
    }

    public void run(){
        System.out.println(getName() + " is waiting ......");
        try {
            semaphore.acquire();
            System.out.println(getName()+" is being servicing ......");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName()+" is done!!!");
        semaphore.release();
    }
}