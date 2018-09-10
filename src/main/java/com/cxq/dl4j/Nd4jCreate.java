package com.cxq.dl4j;

import org.apache.ivy.util.StringUtils;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Created by cxq on 2018/9/6.
 */
public class Nd4jCreate {
    public static void main(String[] args) {
        System.out.println("\n".concat(StringUtils.repeat("-",36)));
        INDArray ones = Nd4j.zeros(2,3);
        System.out.println(ones);

        System.out.println("\n".concat(StringUtils.repeat("-",36)));
        INDArray zores = Nd4j.ones(2,3);
        System.out.println(zores);

        System.out.println("\n".concat(StringUtils.repeat("-",36)));
        INDArray rands = Nd4j.rand(2,3);
        System.out.println(rands);

        System.out.println("\n".concat(StringUtils.repeat("-",36)));
        INDArray randnormals = Nd4j.randn(2,3);
        System.out.println(randnormals);

        System.out.println("\n".concat(StringUtils.repeat("-",36)));
        INDArray array = Nd4j.create(new float[] {1,2,3,4,5,6}, new int[] {2,3});
        System.out.println(array);

    }
}
