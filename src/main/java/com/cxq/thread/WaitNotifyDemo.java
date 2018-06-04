package com.cxq.thread;

/**
 * Created by cxq on 2018/6/3.
 * wait: 使当前线程进入指定对象的等待池
 * notify: 从指定对象等待池中唤醒一个等待线程
 * notifyAll: 从指定对象等待池中唤醒全部等待线程
 * 只有获得该对象的锁后才能调用上述方法
 *
 * 实现 线程A与线程B 的通讯：
 * 1.让A在指定对象的等待池中等待
 * 2.B从指定对象等待池中唤醒A线程
 * remark: B从指定对象等待池中唤醒A线程，A线程不一定马上执行(受操作系统的调调影响)
 */
public class WaitNotifyDemo {
    public static void main(String [] args){
        WaitNotifyDemoData d= new WaitNotifyDemoData();
        Producer p = new Producer(d);
        Consumer c = new Consumer(d);
        p.start();
        c.start();
    }
}

class WaitNotifyDemoData{
    int i;
    public void add(){
        synchronized (this){
            i++;
            if(i%5==0){
                notifyAll();
            }
        }
    }

    public void sub(){
        synchronized (this){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("before: "+ i);
            i -=3;
            System.out.println("after: "+i);
        }
    }
}

class Consumer extends Thread{
    WaitNotifyDemoData data;
    public Consumer(WaitNotifyDemoData data){
        this.data=data;
    }
    public void run(){
        while (true){
            data.sub();
        }
    }
}

class Producer extends Thread{
    WaitNotifyDemoData data;
    public Producer(WaitNotifyDemoData data){
        this.data=data;
    }
    public void run(){
        while (true){
            data.add();
        }
    }
}
