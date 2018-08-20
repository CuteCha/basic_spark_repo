package com.cxq.readFile;

import java.io.*;
import java.util.Arrays;

/**
 * Created by cxq on 2018/8/20.
 */
public class FileReadLine {
    public static void main(String[] args) {
        String filePath = "./output/tmp/data_00.txt";
        long ret = calSum(filePath);
        System.out.println(ret);

    }

    public static long calSum(String filePath) {
        long s = 0;
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            try {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    s += Arrays.stream(line.split("\t")).mapToLong(x -> Long.parseLong(x)).sum();
                }
                bufferedReader.close();
                read.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("can not find " + filePath);
        }
        return s;
    }
}
