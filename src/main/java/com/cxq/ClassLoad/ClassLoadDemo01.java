package com.cxq.ClassLoad;

import com.google.common.base.Strings;
import org.apache.commons.lang.StringUtils;

/**
 * Created by cxq on 2018/9/27.
 */
public class ClassLoadDemo01 {
    private String name;

    public static final int price = 10;

    static {
        System.out.println("Initializing");
    }

    ClassLoadDemo01() {
        System.out.println("Building");
    }

    ClassLoadDemo01(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String playToy(String player) {
        String msg = buildMsg(player);
        System.out.println(msg);
        return msg;
    }

    private String buildMsg(String player) {
        String msg = player + " plays " + this.name;
        return msg;
    }

    public static void main(String[] args) {
        try {
            Class c = Class.forName("com.cxq.ClassLoad.ClassLoadDemo01");
            System.out.println(Strings.repeat("-", 36) + "\nc=" + c);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(StringUtils.repeat("=", 36));
        ClassLoadDemo01 classLoadDemo01 = new ClassLoadDemo01("test");
        System.out.println(classLoadDemo01.getName());
        System.out.println(classLoadDemo01.playToy("xxx") + " ???");


    }
}
