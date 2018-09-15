package com.cxq.singleton;

/**
 * Created by cxq on 2018/9/13.
 * 解决懒汉式中，多线程可能出现同一个对象和效率低的问题
 */
public class SingletonEager {
    private SingletonEager() {
    }

    /*
    * 类加载时就会进行实例化
    * */
    private static SingletonEager instance = new SingletonEager();

    public static SingletonEager getInstance() {
        return instance;
    }

    private static int count = 0;

    public void add(int i) {
        count++;
        System.out.println("i=" + i + "\tcont=" + count);
    }
}
