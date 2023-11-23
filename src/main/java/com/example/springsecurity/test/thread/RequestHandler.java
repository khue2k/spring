package com.example.springsecurity.test.thread;

public class RequestHandler implements Runnable {
    private String name;

    public RequestHandler(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            //thread bắt đầu chạy
            System.out.println(Thread.currentThread().getName() + " Starting process : " + name);

            //cho thread ngủ 1s coi như là cái thread này chạy hết 1s mới xong được
            Thread.sleep(500);

            //thread đã chạy xong
            System.out.println(Thread.currentThread().getName() + " Finished process : " + name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
