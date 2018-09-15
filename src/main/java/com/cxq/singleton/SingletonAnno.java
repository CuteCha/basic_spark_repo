package com.cxq.singleton;

/**
 * Created by cxq on 2018/9/15.
 * 单例：在应用程序中保证只有一个实例
 * 好处：1、提升运行效率；2、实现数据共享；
 * <p>
 * 类的加载：1、加载全局static的属性方法；2、加载全局可赋值的对象；---加载到栈和堆中
 * 3、加载构造方法；---此时才会把类调用出来
 */
public class SingletonAnno {
    /* 构成方法：方法名和类名相同；无返回值
    *
    * 一、构成方法私有化---其他类不能访问这个类对象
    * 二、对外提供访问入口
    *
    * */
    private SingletonAnno() {
    }

    /*
    * 定义全局的变量用来判断是否实例化过
    * 因为静态方法不能调用非静态变量，所以需要把变了设置成static
    * */
    private static SingletonAnno singletonAnno;

    /*
    * 实例方法，实例方法必须通过对象调用
    * 所以需要设置方法为静态方法
    * */
    public static SingletonAnno getInstance() {
        /*
        * 添加逻辑如果已经实例化过，则直接返回
        * 因此需要全局的变量用来做判断
        * */
        if (singletonAnno == null) {
            /*
            * 保证线程安全，需要加锁
            * */
            synchronized (SingletonAnno.class) {
                /*
                * 双重验证
                * */
                if (singletonAnno == null) {
                    singletonAnno = new SingletonAnno();
                }
            }
        }

        return singletonAnno;
    }

}
