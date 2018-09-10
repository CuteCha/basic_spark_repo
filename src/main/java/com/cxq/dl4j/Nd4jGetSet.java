package com.cxq.dl4j;

import org.apache.ivy.util.StringUtils;
import org.nd4j.linalg.api.iter.NdIndexIterator;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Arrays;

/**
 * Created by cxq on 2018/9/6.
 */
public class Nd4jGetSet {
    public static void main(String[] args) {
        INDArray array = Nd4j.create(new float[]{1, 2, 3, 4, 5, 6}, new int[]{2, 3});
        float value = array.getFloat(1, 1);
        System.out.println(value);

        System.out.println("\n".concat(StringUtils.repeat("-", 36)));
        System.out.println(array);

        array.put(1, 0, 7);
        System.out.println("\n".concat(StringUtils.repeat("-", 36)));
        System.out.println(array);

        System.out.println("\n".concat(StringUtils.repeat("-", 36)));
        NdIndexIterator indexIterator = new NdIndexIterator(2, 3);
        while (indexIterator.hasNext()) {
            long[] i = indexIterator.next();
            double v = array.getDouble(i);

            System.out.println(Arrays.toString(i) + ":\t" + v);
        }

        System.out.println("\n".concat(StringUtils.repeat("-", 36)));
        long[] shape = array.shape();
        for (long i : shape) {
            System.out.println(i);
        }
        System.out.println(Arrays.toString(shape));

        System.out.println("\n".concat(StringUtils.repeat("-", 36)));
        INDArray row=array.getRow(0);
        System.out.println(row);
        INDArray rows=array.getRows(0,1);
        System.out.println(rows);

        System.out.println("\n".concat(StringUtils.repeat("-", 36)));
        INDArray col = array.getColumn(0);
        System.out.println(col);
        INDArray cols = array.getColumns(0,2);
        System.out.println(cols);

        System.out.println("\n".concat(StringUtils.repeat("-", 36)));
        INDArray rRow=Nd4j.create(new float[]{1,1,1});
        array.putRow(0,rRow);
        System.out.println(array);
        System.out.println("\n".concat(StringUtils.repeat("-", 36)));



    }
}
