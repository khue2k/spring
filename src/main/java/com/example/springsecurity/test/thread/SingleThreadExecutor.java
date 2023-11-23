package com.example.springsecurity.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 100; i++) {
            executor.execute(new RequestHandler("request : " + i));
            Thread.sleep(200);
        }
        executor.shutdown();
    }
}
