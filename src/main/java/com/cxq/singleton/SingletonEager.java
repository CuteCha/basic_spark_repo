package com.cxq.singleton;

/**
 * Created by cxq on 2018/9/13.
 */
public class SingletonEager {
    private SingletonEager(){}
    private static SingletonEager instance= new SingletonEager();
    public  static SingletonEager getInstance(){
        return instance;
    }
}
