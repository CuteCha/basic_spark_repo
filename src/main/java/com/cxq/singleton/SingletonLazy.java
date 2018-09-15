package com.cxq.singleton;

/**
 * Created by cxq on 2018/9/13.
 */
public class SingletonLazy {
    private volatile static SingletonLazy singletonLazy;
    private SingletonLazy(){}
    public static SingletonLazy getInstance(){
        if(singletonLazy==null){
            synchronized (SingletonLazy.class){
                if(singletonLazy==null){
                    singletonLazy=new SingletonLazy();
                }
            }
        }

        return singletonLazy;
    }
}
