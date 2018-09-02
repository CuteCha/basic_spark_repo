package matrix;

import org.apache.log4j.PropertyConfigurator;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Created by cxq on 2018/8/31.
 */
public class MatrixOperation {
    public static void main(String[] args) {
//        PropertyConfigurator.configure("./conf/log4j.properties");
        INDArray arr1 = Nd4j.create(new float[]{1,2,3,4},new int[]{2,2});
        System.out.println(arr1);
    }
}
