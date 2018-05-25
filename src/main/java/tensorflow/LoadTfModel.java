package tensorflow;

import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;

import java.io.IOException;
import java.nio.FloatBuffer;

/**
 * Created by cxq on 2018/5/24.
 */
public class LoadTfModel {
    public static void main(String[] args) throws IOException {
        // good idea to print the version number, 1.2.0 as of this writing
        System.out.println(TensorFlow.version());
        final int NUM_PREDICTIONS = 1;

        /* load the model Bundle */
        String modelDir="./output/tmp/model";
        SavedModelBundle b = SavedModelBundle.load(modelDir, "serve");

        // create the session from the Bundle
        Session sess = b.session();
        // create an input Tensor, value = 2.0f
        Tensor x = Tensor.create(
                new long[]{NUM_PREDICTIONS},
                FloatBuffer.wrap(new float[]{2.0f})
        );

        // run the model and get the result, 4.0f.
        float[] y = sess.runner()
                .feed("x", x)
                .fetch("y")
                .run()
                .get(0)
                .copyTo(new float[NUM_PREDICTIONS]);

        // print out the result.
        System.out.println(y[0]);
    }
}
