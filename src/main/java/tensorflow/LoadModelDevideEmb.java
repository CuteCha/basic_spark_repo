package tensorflow;

import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cxq on 2018/8/7.
 */
public class LoadModelDevideEmb {
    public static void main(String[] args) {
        String modelDir = "./output/tmp/devide_emb_pb";
        SavedModelBundle bundle = SavedModelBundle.load(modelDir, "rnet_serve");
        Session sess = bundle.session();

        int[][] q_idx0 = new int[1][5];
        int[][] p_idx0 = new int[1][15];

        float[][][] q_emb0 = new float[1][5][64];
        float[][][] p_emb0 = new float[1][15][64];

        for (Integer i = 0; i < 5; i++) {
            q_idx0[0][i] = 1;
            System.out.print(q_idx0[0][i]+"\t");
        }
        System.out.println("\n--------------------------------");
        for (Integer i = 0; i < 15; i++) {
            p_idx0[0][i] = 1;
            System.out.print(p_idx0[0][i]+"\t");
        }
        System.out.println("\n--------------------------------");
        for (Integer i = 0; i < 5; i++) {
            for (Integer j = 0; j < 64; j++) {
                q_emb0[0][i][j] =(i+1)*0.1f;
                System.out.print(q_emb0[0][i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
        for (Integer i = 0; i < 15; i++) {
            for (Integer j = 0; j < 64; j++) {
                p_emb0[0][i][j] =(i+1)*0.1f;
                System.out.print(p_emb0[0][i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("--------------------------------");

        Tensor<Float> q_emb = Tensor.create(q_emb0, Float.class);
        Tensor<Integer> q_idx = Tensor.create(q_idx0, Integer.class);
        Tensor<Float> p_emb = Tensor.create(p_emb0, Float.class);
        Tensor<Integer> p_idx = Tensor.create(p_idx0, Integer.class);

        long st = System.currentTimeMillis();
        List<Tensor<?>> res = sess.runner()
                .feed("query", q_idx)
                .feed("passage", p_idx)
                .feed("c_emb", p_emb)
                .feed("q_emb", q_emb)
                .fetch("predict/start_position")
                .fetch("predict/end_position")
                .fetch("pointer/start_prob")
                .fetch("pointer/end_prob")
                .run();

        Tensor p1 = res.get(0);
        Tensor p2 = res.get(1);
        Tensor prob1 = res.get(2);
        Tensor prob2 = res.get(3);

        long[] s = new long[1];
        p1.copyTo(s);
        long[] e = new long[1];
        p2.copyTo(e);
        float[][] ps = new float[1][15];
        prob1.copyTo(ps);
        float[][] pe = new float[1][15];
        prob2.copyTo(pe);
        long et = System.currentTimeMillis();
        System.out.println("cost: " + (et - st) + " ms");
        System.out.println(s[0]);
        System.out.println(e[0]);
        for (Integer i = 0; i < ps[0].length; i++) {
            System.out.println(ps[0][i] + "\t" + pe[0][i]);
        }

        q_emb.close();
        q_idx.close();
        p_emb.close();
        p_idx.close();
    }
}
