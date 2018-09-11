package com.cxq.readFile;


import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by cxq on 2018/8/21.
 */
public class ReadSum {
    private static RandomAccessFile randomAccessFile;
    private static int threadNum;
    private static long fileSize;
    private static long blockNum;
    private static ExecutorService executorService;
    private static Semaphore semaphore;
    private static CountDownLatch countDownLatch;
    private static Set<StartEndPos> stPairs;
    private static long sum = 0;


    private ReadSum(String fileName, int threadNum) {
        try {
            this.randomAccessFile = new RandomAccessFile(fileName, "rw");
            this.fileSize = randomAccessFile.length();
            this.threadNum = threadNum;
            this.blockNum = fileSize / this.threadNum;
            this.stPairs = new HashSet<>();
            this.executorService = Executors.newFixedThreadPool(threadNum);
            this.semaphore = new Semaphore(threadNum);
            this.countDownLatch = new CountDownLatch(threadNum);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void start() {
        long startTime = System.currentTimeMillis();
        System.out.println("start ...");
        splitFile();
        System.out.println("stPairs.size: " + stPairs.size());

        for (StartEndPos stPos : stPairs) {
//            this.executorService.submit(new ReadSum.sumFun(stPos.startPos, stPos.endPos));
            this.executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    sumFunc(stPos.startPos, stPos.endPos);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });

        }

        try {
            countDownLatch.await();
            randomAccessFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.executorService.shutdown();
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("cost time: %dms", endTime - startTime));
        System.out.println("result: " + this.sum);
    }

    public void splitFile() {
        long s = 0;
        long t;
        String line = null;
        for (int i = 1; i < threadNum; i++) {
            try {
                randomAccessFile.seek(blockNum * i);
                line = randomAccessFile.readLine();
                t = randomAccessFile.getFilePointer();
                StartEndPos st = new StartEndPos(s, t);
                s = t;
                stPairs.add(st);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        stPairs.add(new StartEndPos(s, fileSize));
    }

    public static synchronized void sumFunc(long start, long end) {
        long k;
        long temp = 0;
        String line = null;
        k = start;

        try {
            randomAccessFile.seek(start);
            while (k < end) {
                line = randomAccessFile.readLine();
                String[] lineArr = line.split("\t");
                for (String each : lineArr) {
                    temp += Long.parseLong(each);
                }
//                temp += Arrays.stream(line.split("\t")).mapToLong(x -> Long.parseLong(x)).sum();
                k = randomAccessFile.getFilePointer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sum += temp;
    }

    public static void main(String[] args) throws Exception {
        String fileName = "/Users/cxq/PycharmProjects/test/work/multithreads/data.txt";
        new ReadSum(fileName, 1).start();
//        someDebug();
//        testSplitFile(fileName, 4);//0,780,1530,2280,3000
//        testRead(fileName, 0, 780);
//        testRead(fileName, 780, 1530);
//        testRead(fileName, 1530, 2280);
//        testRead(fileName, 2280, 3000);
//        testRead(fileName, 0, 3000);

    }

    public static void testSplitFile(String fileName, int threadNum) throws Exception {
        long s = 0;
        long t;
        String line;
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        long fileSize = raf.length();
        long blockNum = fileSize / threadNum;
        for (int i = 1; i < threadNum; i++) {
            raf.seek(blockNum * i);
            line = raf.readLine();
            t = raf.getFilePointer();
            System.out.println("s: " + s + "\tt: " + t);
            s = t;
        }
        System.out.println("s: " + s + "\tt: " + fileSize);
    }

    public static void testRead(String fileName, long s, long t) throws Exception {
        long k;
        long sum = 0;
        String line;
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        k = s;
        raf.seek(s);
        List<String> ret = new ArrayList<>();
        while (k < t) {
            line = raf.readLine();
            System.out.println(line);
            ret.add(line);
            sum += Arrays.stream(line.split("\t")).mapToLong(x -> Long.parseLong(x)).sum();
            k = raf.getFilePointer();
        }
        System.out.println("=====" + ret.size() + " sum=" + sum);
    }

    public static void someDebug() throws Exception {
        String fileName = "/Users/cxq/PycharmProjects/test/work/multithreads/data.txt";
//        String fileName="./output/tmp/rafTestData.txt";
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        String line;
        long fileSize = raf.length();
        System.out.println(fileSize);
        System.out.println(raf.getFilePointer());
        raf.seek(5);
        System.out.println(raf.getFilePointer());
        raf.seek(0);
        byte[] b = new byte[29];
        int i = raf.read(b);
        System.out.println(i);
        System.out.println(new String(b));
        System.out.println("------------");
        raf.seek(0);
        line = raf.readLine();
//        line=new String(raf.readLine().getBytes("ISO-8859-1"), "utf-8"); //解决中文乱码问题
        System.out.println(line);
        System.out.println(raf.getFilePointer());
//        raf.seek(raf.getFilePointer()+1);
        line = raf.readLine();
        System.out.println(line);
        System.out.println(raf.getFilePointer());
        System.out.println("------------");
        byte[] c = new byte[1];
        int j = raf.read(c);
        System.out.println(new String(c));
        int k = raf.read(c);
        System.out.println(new String(c));
        int l = raf.read(c);
        System.out.println(new String(c));
        System.out.println("================");
        long m = 0;
        raf.seek(0);
        m = raf.getFilePointer();
        System.out.println(m);
        line = raf.readLine();
        System.out.println(line);
        System.out.println(raf.getFilePointer());
//        raf.seek(raf.getFilePointer()+1);
        System.out.println(raf.readLine());
        System.out.println(raf.readLine());
        raf.seek(30);
        System.out.println(raf.readLine());

//        System.out.println(raf.read(c));
//        System.out.println(new String(c));
//        m = raf.getFilePointer();
//        System.out.println(m);
//        System.out.println("+++++");
//        System.out.println(raf.read(c));
//        System.out.println(new String(c));
//        m = raf.getFilePointer();
//        System.out.println(m);
    }


    class StartEndPos {
        long startPos;
        long endPos;

        private StartEndPos(long startPos, long endPos) {
            this.startPos = startPos;
            this.endPos = endPos;
        }
    }

    private class sumFun implements Runnable {
        private long start;
        private long end;
        private long total = 0;

        public sumFun(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            long k;
            String line;
            k = start;

            try {
                randomAccessFile.seek(start);
                while (k < end) {
                    line = randomAccessFile.readLine();
                    total += Arrays.stream(line.split("\t")).mapToLong(x -> Long.parseLong(x)).sum();
                    k = randomAccessFile.getFilePointer();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            sum += total;
//            runThreadNum--;
//            if(runThreadNum==0){
//                endTime = System.currentTimeMillis();
//                System.out.println("finish ^_^");
//                System.out.println("cost time: "+ (endTime - startTime));
//                System.out.println("result: "+ sum);
//                try {
//                    randomAccessFile.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                executorService.shutdown();
//            }
        }
    }
}
