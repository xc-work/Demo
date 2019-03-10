package com.example.thread.createThread;

/**
 * Created by Francis on 2019/3/4.
 */
public class ThreadTest2 {

    public static class RunableTask implements Runnable{
        @Override
        public void run() {
            System.out.println("I am a child thread");
        }
    }

    public static void main(String[] args) {
        RunableTask task = new RunableTask();
        new Thread(task).start();
        new Thread(task).start();
    }

}


