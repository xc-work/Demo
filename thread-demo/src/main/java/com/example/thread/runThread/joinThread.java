package com.example.thread.runThread;

/**
 * Created by Francis on 2019/3/5.
 */
public class joinThread {

    public static void main(String[] args) throws InterruptedException {

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child threadOne over");
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child chreadTwo over");
            }
        });

        threadOne.start();
        threadTwo.start();
        System.out.println("wait all child thread over");

        threadOne.join();
        threadTwo.join();
        System.out.println("all child chread over");

    }
}
