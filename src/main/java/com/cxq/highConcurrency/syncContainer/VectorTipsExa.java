package com.cxq.highConcurrency.syncContainer;

import org.apache.ivy.util.StringUtils;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Created by cxq on 2018/8/30.
 */
public class VectorTipsExa {

    // java.util.ConcurrentModificationException
    public void foreachRemove(Vector<Integer> v) {
        for (Integer i : v) {
            if (i.equals(3)) {
                v.remove(i);
            }
        }
    }

    // java.util.ConcurrentModificationException
    public void iteratorRemove(Vector<Integer> v) {
        Iterator<Integer> iterator = v.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                v.remove(i);
            }
        }
    }

    public void forRemove(Vector<Integer> v) {
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i).equals(3)) {
                v.remove(i);
            }
        }
    }

    /**
     * vector remove use for loop
     */
    @Test
    public void testVectorRemove() {
        Vector<Integer> vector = new Vector<>();
        for (int i = 0; i < 3; i++) {
            vector.add(i);
        }
        forRemove(vector);
    }

    @Test
    public void testVector() {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(3);
        vector.add(4);
        vector.add(5);
        vector.add(6);

        for (Integer i : vector) {
            System.out.print(i + "\t");
        }
        System.out.println();
        System.out.println(StringUtils.repeat("-", 36));
        vector.remove(3);
        for (Integer i : vector) {
            System.out.print(i + "\t");
        }
    }

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread removeOpThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            });
            Thread getOpThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        System.out.println(vector.get(i));
                    }
                }
            });

            removeOpThread.start();
            getOpThread.start();
        }
    }
}
