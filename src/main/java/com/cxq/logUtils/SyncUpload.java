package com.cxq.logUtils;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncUpload {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SyncUpload.class);
    private String feature;


    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public void parseFeature() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("parse feature: " + getFeature());
    }

    public void sendFeature() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("send feature: " + getFeature());
    }

    public void debug() {
        log.info("main thread start ......");
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            SyncUpload syncUpload1 = new SyncUpload();
            syncUpload1.setFeature("test1_" + i);
            executorService.execute(() -> {
                syncUpload1.parseFeature();
            });

            SyncUpload syncUpload2 = new SyncUpload();
            syncUpload2.setFeature("test2_" + i);
            executorService.execute(() -> {
                syncUpload2.sendFeature();
            });

        }

        executorService.shutdown();

        log.info("main thread end ......");
    }

    public static void main(String[] args) {
        new SyncUpload().debug();
    }
}
