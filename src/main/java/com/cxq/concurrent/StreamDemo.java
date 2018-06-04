package com.cxq.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by cxq on 2018/6/4.
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<String> lst = new ArrayList<>();
        lst.add("abc");
        lst.add("efg");
        lst.add("tcp");
        lst.add("bfp");

        Optional<String> max = lst.stream().max(String::compareTo);
        System.out.println(max.get());
        System.out.println("------\n");
        lst.stream().sorted().forEach(x -> System.out.println(x));
        System.out.println("------\n");
        System.out.println(lst.stream().count());
    }
}
