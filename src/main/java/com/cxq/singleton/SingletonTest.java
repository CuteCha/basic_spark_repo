package com.cxq.singleton;

import org.junit.Test;

/**
 * Created by cxq on 2018/9/13.
 */
public class SingletonTest {

    @Test
    public void testEager(){
        SingletonEager singletonEager1 = SingletonEager.getInstance();
        SingletonEager singletonEager2 = SingletonEager.getInstance();
        System.out.println(singletonEager1==singletonEager2);
        System.out.println(singletonEager1.equals(singletonEager2));
    }

    @Test
    public void testLazy(){
        SingletonLazy singletonLazy1= SingletonLazy.getInstance();
        SingletonLazy singletonLazy2= SingletonLazy.getInstance();
        System.out.println(singletonLazy1==singletonLazy2);
        System.out.println(singletonLazy1.equals(singletonLazy2));
    }

    @Test
    public void testSingletonAnno(){
        SingletonAnno singletonAnno1=SingletonAnno.getInstance();
        SingletonAnno singletonAnno2=SingletonAnno.getInstance();
        System.out.println(singletonAnno1==singletonAnno2);
        System.out.println(singletonAnno1.equals(singletonAnno2));
    }

}
