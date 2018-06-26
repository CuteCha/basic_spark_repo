package tensorflow;

import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import java.nio.IntBuffer;
import java.util.List;

/**
 * Created by cxq on 2018/6/13.
 */
public class LoadModelSignature {
    public static void main(String[] args) {
        String modelDir = "./output/tmp/model2";
        SavedModelBundle bundle = SavedModelBundle.load(modelDir, "rnet_serve");
        Session sess = bundle.session();

        final int NUM_PREDICTIONS = 1;
        //s=7;e=7
//        int[] q_idx0 = {43, 10344, 263, 12, 26, 9392, 7, 52, 65, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//        int[] p_idx0 = {5827, 263, 12, 26, 256, 7, 1063, 7801, 6, 44, 144, 28843, 563, 10411, 58, 9422, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        //s=0;e=1
        int[] q_idx0 = {24, 554, 1257, 25, 2993, 6, 2110, 15614, 412, 4744, 5, 3978, 7, 52, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] p_idx0 = {412, 790, 6, 260, 1099, 973, 6, 9152, 64, 22845, 38, 6, 509, 15614, 412, 4744, 3978, 10, 23225, 31815, 6, 511, 4755, 6, 18433, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        Tensor q_idx = Tensor.create(
                new long[]{1, 50},
                IntBuffer.wrap(q_idx0)
        );
        Tensor p_idx = Tensor.create(
                new long[]{1, 400},
                IntBuffer.wrap(p_idx0)
        );

        long st = System.currentTimeMillis();
        List<Tensor<?>> res = sess.runner()
                .feed("query", q_idx)
                .feed("passage", p_idx)
                .fetch("predict/start_position")
                .fetch("predict/end_position")
                .run();
        Tensor p1 = res.get(0);
        Tensor p2 = res.get(1);
        long[] s = (long[]) p1.copyTo(new long[1]);
        long[] e = (long[]) p2.copyTo(new long[1]);
        long et = System.currentTimeMillis();
        System.out.println("cost: " + (et - st) + " ms");
        System.out.println(s[0]);
        System.out.println(e[0]);

        System.out.println("====================");
        long st2 = System.currentTimeMillis();
        List<Tensor<?>> res2 = sess.runner()
                .feed("query", q_idx)
                .feed("passage", p_idx)
                .fetch("predict/start_position")
                .fetch("predict/end_position")
                .run();
        long et2 = System.currentTimeMillis();
        System.out.println("cost: " + (et2 - st2) + " ms");
        System.out.println(parseIndex(res2.get(0)));
        System.out.println(parseIndex(res2.get(1)));

        for (int i = 0; i < 10; i++) {
            System.out.println("====================");
            long st3 = System.currentTimeMillis();
            List<Tensor<?>> res3 = sess.runner()
                    .feed("query", q_idx)
                    .feed("passage", p_idx)
                    .fetch("predict/start_position")
                    .fetch("predict/end_position")
                    .run();
            long et3 = System.currentTimeMillis();
            System.out.println("cost: " + (et3 - st3) + " ms");
            System.out.println(parseIndex(res3.get(0)));
            System.out.println(parseIndex(res3.get(1)));
        }

//        System.out.println(y.toString());
//        System.out.println(y.dataType());
//        System.out.println(y.copyTo(new long[1]));

//        Tensor y = sess.runner()
//                .feed("query", q_idx)
//                .feed("passage", p_idx)
//                .fetch("predict/start_position")
//                .fetch("predict/end_position")
//                .run()
//                .get(0);

//        int matrix[][] = {{1, 2}, {3, 4}};
//        Tensor t = Tensor.create(matrix);
//        int[][] z = (int[][]) t.copyTo(new int[2][2]);
//        System.out.println(t.copyTo(new int[2][2]));
//        System.out.println(z[1][0]);

        sess.close();
    }

    public static long parseIndex(Tensor p) {
        long[] idx = (long[]) p.copyTo(new long[1]);
        return idx[0];
    }
}
