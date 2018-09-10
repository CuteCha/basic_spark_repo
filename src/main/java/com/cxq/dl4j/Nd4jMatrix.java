package com.cxq.dl4j;

import org.apache.ivy.util.StringUtils;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.inverse.InvertMatrix;

/**
 * Created by cxq on 2018/9/6.
 */
public class Nd4jMatrix {
    public static void main(String[] args) {
        INDArray a= Nd4j.create(new float[]{1,0,0,1}, new int[]{2,2});
        INDArray b= Nd4j.create(new float[]{1,2,3,4}, new int[]{2,2});
        System.out.println(a.mmul(b));
        System.out.println("\n".concat(StringUtils.repeat("-", 36)));
        System.out.println(b.mmul(a));
        System.out.println("\n".concat(StringUtils.repeat("-", 36)));
        System.out.println(b.add(a));
        System.out.println("\n".concat(StringUtils.repeat("-", 36)));
        System.out.println(b.sub(a));
        System.out.println("\n".concat(StringUtils.repeat("-", 36)));
        System.out.println(InvertMatrix.invert(b,false));
    }
}
