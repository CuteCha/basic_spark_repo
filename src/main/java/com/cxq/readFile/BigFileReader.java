package com.cxq.readFile;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cxq on 2018/9/10.
 */
public class BigFileReader {
    //    private final static String fileName = "/Users/cxq/PycharmProjects/test/work/multithreads/data.txt";
    private final static String fileName = "./output/tmp/data_00.txt";
    private final static int threadNum = 4;
    private static long sum = 0;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        long startTime = System.currentTimeMillis();
        Set<StartEndLine> startEndLines = splitFile(fileName, threadNum);
        for (StartEndLine startEndLine : startEndLines) {
            executorService.execute(() -> {
                try {
                    add1(fileName, startEndLine);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        executorService.shutdown();
        System.out.println("cost: " + (System.currentTimeMillis() - startTime));
        System.out.println("sum: " + sum);

    }

    public static void add(String fileName, StartEndLine startEndLine) throws Exception {
        long k;
        long temp = 0;
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        String line = null;
        k = startEndLine.start;

        try {
            raf.seek(k);
            while (k < startEndLine.end) {
                line = raf.readLine();
                temp += Arrays.stream(line.split("\t")).mapToLong(x -> Long.parseLong(x)).sum();
                k = raf.getFilePointer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sum += temp;
        raf.close();
    }

    public static void add1(String fileName, StartEndLine startEndLine) throws Exception {
        long temp = 0;
        int k = 0;
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");

        try {
            MappedByteBuffer mappedByteBuffer = raf.getChannel().map(FileChannel.MapMode.READ_ONLY, startEndLine.start, startEndLine.end - startEndLine.start + 1);
            while (mappedByteBuffer.hasRemaining()) {
                byte b = mappedByteBuffer.get();
                if (b != '\n' && b != '\r' && b != '\t') {
                    int result = Character.getNumericValue((int) ((char) b));
                    if (k == 0) {
                        temp += (result * 10);
                        k = 1;
                    } else {
                        temp += result;
                        k = 0;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sum += temp;
        raf.close();
    }

    public static Set<StartEndLine> splitFile(String fileName, int threadNum) throws Exception {
        Set<StartEndLine> startEndLines = new HashSet<>();
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        long fileSize = raf.length();
        long blockNum = fileSize / threadNum;
        long s = 0;
        long t;
        String line = null;
        for (int i = 1; i < threadNum; i++) {
            try {
                raf.seek(blockNum * i);
                line = raf.readLine();
                t = raf.getFilePointer();
                StartEndLine startEndLine = new StartEndLine(s, t);
                s = t;
                startEndLines.add(startEndLine);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        startEndLines.add(new StartEndLine(s, fileSize));
        raf.close();

        return startEndLines;

    }

    static class StartEndLine {
        long start;
        long end;

        private StartEndLine(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }
}
