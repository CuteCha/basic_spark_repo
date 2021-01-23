package com.cxq.singleton;

/**
 * Created by cxq on 2021/1/23 12:08 下午
 */
public class SingletonVolatile {
    private SingletonVolatile(){}
    private static volatile SingletonVolatile singletonVolatile;

    public static SingletonVolatile getInstance(){
        if (singletonVolatile == null){
            synchronized (SingletonVolatile.class) {
                if (singletonVolatile == null) {
                    singletonVolatile = new SingletonVolatile();
                }
            }
        }

        return singletonVolatile;
    }

    public void showMessage(int i) {
        System.out.println("Hello World " + i);
    }

    private static int count=0;
    public void add(int i){
        count++;
        System.out.println("i="+i+"\t"+"count="+count);
    }
}
