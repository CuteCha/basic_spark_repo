package com.cxq.concurrent;

import java.util.concurrent.Exchanger;

/**
 * Created by cxq on 2018/6/3.
 */
public class ExchangeDemo {
    public static void main(String [] args){
        Exchanger<String> ex = new Exchanger<>();
        new ExchangeDemoA(ex,"1").start();
        new ExchangeDemoB(ex,"2").start();
    }
}

class ExchangeDemoA extends Thread {
    private Exchanger<String> ex;

    public ExchangeDemoA(Exchanger<String> ex, String name) {
        this.ex = ex;
        setName(name);
    }

    public void run() {
        String str = null;

        try {
            str = ex.exchange("Hello");
            System.out.println(getName() + ": " + str);
            str = ex.exchange("A");
            System.out.println(getName() + ": " + str);
            str = ex.exchange("B");
            System.out.println(getName() + ": " + str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ExchangeDemoB extends Thread {
    private Exchanger<String> ex;

    public ExchangeDemoB(Exchanger<String> ex, String name) {
        this.ex = ex;
        setName(name);
    }

    public void run() {
        String str = null;

        try {
            str = ex.exchange("hi");
            System.out.println(getName() + ": " + str);
            str = ex.exchange("a");
            System.out.println(getName() + ": " + str);
            str = ex.exchange("b");
            System.out.println(getName() + ": " + str);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}