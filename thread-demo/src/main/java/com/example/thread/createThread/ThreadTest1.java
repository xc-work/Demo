package com.example.thread.createThread;

/**
 * Created by Francis on 2019/3/4.
 */
public class ThreadTest1 {

    public static class MyThread extends Thread{
        @Override
        public void run(){
            System.out.println("I am a child thread");
        }
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}
