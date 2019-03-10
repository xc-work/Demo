package com.example.jpalockdemo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Francis on 2019/3/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NodeServiceTest {

    @Autowired
    private NodeService nodeService;

    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

    @Test
    public void add() throws Exception {
        for (int i = 0; i < 100; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    nodeService.add();
                    System.out.println(index);

//                    try {
//                        System.out.println(index);
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
                }
            });
        }
//        orderService.add();
    }

}