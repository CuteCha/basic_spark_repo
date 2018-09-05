package matrix;

import org.apache.ivy.util.StringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Created by cxq on 2018/8/31.
 */
public class MatrixOperation {
    public static void testForLoop() {
        long s = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i <= 1000000; i++) {
            s += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println(StringUtils.repeat("-", 36));
        System.out.println(s);
        System.out.println(endTime - startTime);
    }

    public static void main(String[] args) {
        PropertyConfigurator.configure("./conf/log4j.properties");
        INDArray arr1 = Nd4j.create(new float[]{1, 2, 3, 4}, new int[]{2, 2});
        System.out.println(arr1);
        INDArray a = Nd4j.create(new float[]{1,2}, new int[]{1,2});
        INDArray b = Nd4j.create(new float[]{3,4}, new int[]{2,1});
        INDArray c= Nd4j.create(new float[]{1,1,1,1,1,1}, new int[]{2,3});
        System.out.println("a*b= "+a.mmul(b));
        System.out.println("a*c="+a.mmul(c));
        System.out.println("c*c^T="+c.mmul(c.transpose()));
        System.out.println("c^T*b="+c.transpose().mmul(b));
        System.out.println("b*a="+b.mmul(a));
        System.out.println(a.shape());
    }
}
