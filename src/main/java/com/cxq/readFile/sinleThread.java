package com.cxq.readFile;

import java.io.*;

/**
 * Created by cxq on 2018/8/10.
 */
public class sinleThread {
    public static void main(String[] args) {
        String filePath = "./output/tmp/data_00.txt";
        long s = 0;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(new File(filePath)));
            String line = null;
            long row = 0;
            while ((line = in.readLine()) != null) {
                String[] temp = line.split("\t");
                for (int j = 0; j < 10; j++) {
                    s += Long.valueOf(temp[j]);
                }
                row++;
                if (row % 10000 == 0) {
                    System.out.println(row);
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("s=" + s);
    }
}
