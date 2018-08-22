package com.cxq.readFile;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by cxq on 2018/8/21.
 */
public class RandAccFileDemo {
    public static void main(String[] args) throws IOException {
        String fileName = "./output/tmp/rafTestData.txt";
        RandomAccessFile rf = new RandomAccessFile(fileName, "rw");
        for (int i = 0; i < 10; i++) {
            //写入基本类型double数据
            rf.writeDouble(i * 1.414);
//            rf.writeBytes(Double.valueOf(i*1.414).toString().concat("\n"));
        }
        rf.close();
        outLocalFile(fileName);
        rf = new RandomAccessFile(fileName, "rw");
        //直接将文件指针移到第5个double数据后面
        rf.seek(5 * 8);
        //覆盖第6个double数据
        rf.writeDouble(47.0001);
        rf.close();
        outLocalFile(fileName);
    }

    public static void outLocalFile(String fileName) throws IOException {
        RandomAccessFile rf = new RandomAccessFile(fileName, "r");
        for (int i = 0; i < 10; i++) {
            System.out.println("Value " + i + ": " + rf.readDouble());
//            System.out.println("Value " + i + ": " + rf.readLine());
        }
        rf.close();
    }

}
