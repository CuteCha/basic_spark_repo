package tensorflow;

import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import java.io.*;
import java.util.List;

/**
 * Created by cxq on 2018/8/9.
 */
public class LoadEmbLen {
    public static void main(String[] args) {
        String modelDir = "./output/tmp/devide_emb_len_pb";
        SavedModelBundle bundle = SavedModelBundle.load(modelDir, "rnet_serve");
        Session sess = bundle.session();

        int[] q_len0 = new int[1];
        int[] p_len0 = new int[1];
        q_len0[0] = 5;
        p_len0[0] = 17;

        float[][][] q_emb0 = null;
        float[][][] p_emb0 = null;

        q_emb0 = readSample("./output/tmp/q_emb.txt", 5);
        p_emb0 = readSample("./output/tmp/p_emb.txt", 17);

        for (Integer i = 0; i < 5; i++) {
            for (Integer j = 0; j < 64; j++) {
                System.out.print(q_emb0[0][i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("--------------------------------");

        for (Integer i = 0; i < 17; i++) {
            for (Integer j = 0; j < 64; j++) {
                System.out.print(p_emb0[0][i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("--------------------------------");

//        float[][][] q_emb0 = new float[1][5][64];
//        float[][][] p_emb0 = new float[1][15][64];

//        for (Integer i = 0; i < 5; i++) {
//            for (Integer j = 0; j < 64; j++) {
//                q_emb0[0][i][j] = (i + 1) * 0.1f;
//                System.out.print(q_emb0[0][i][j] + "\t");
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------------------");
//
//        for (Integer i = 0; i < 15; i++) {
//            for (Integer j = 0; j < 64; j++) {
//                p_emb0[0][i][j] = (i + 1) * 0.1f;
//                System.out.print(p_emb0[0][i][j] + "\t");
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------------------");

        Tensor<Float> q_emb = Tensor.create(q_emb0, Float.class);
        Tensor<Integer> q_len = Tensor.create(q_len0, Integer.class);
        Tensor<Float> p_emb = Tensor.create(p_emb0, Float.class);
        Tensor<Integer> p_len = Tensor.create(p_len0, Integer.class);

        long st = System.currentTimeMillis();
        List<Tensor<?>> res = sess.runner()
                .feed("c_len", p_len)
                .feed("q_len", q_len)
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
        float[][] ps = new float[1][17];
        prob1.copyTo(ps);
        float[][] pe = new float[1][17];
        prob2.copyTo(pe);
        long et = System.currentTimeMillis();
        System.out.println("cost: " + (et - st) + " ms");
        System.out.println(s[0]);
        System.out.println(e[0]);
        for (Integer i = 0; i < ps[0].length; i++) {
            System.out.println(ps[0][i] + "\t" + pe[0][i]);
        }
    }

    public static float[][][] readSample(String filePath, int len) {
        float[][][] emb = new float[1][len][64];
        File file = new File(filePath);
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            String line = null;
            int row = 0;
            while ((line = in.readLine()) != null) {
                String[] temp = line.split("\t");
                for (int j = 0; j < 64; j++) {
                    emb[0][row][j] = Float.valueOf(temp[j]);
                }
                row++;
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emb;
    }
}
