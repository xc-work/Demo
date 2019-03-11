package com.example.jpalockdemo.controller;

import com.example.jpalockdemo.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Francis on 2019/3/9.
 */
@RestController
public class NodeController {

    @Autowired
    private NodeService nodeService;
    private AtomicInteger i = new AtomicInteger(0);

    @GetMapping(value = "/nodeAdd")
    public String test() throws InterruptedException {
        boolean flag = false;
        while (!flag){
            flag = nodeService.add();
            if (flag){
                System.out.println(i.addAndGet(1));
            }
            Thread.sleep(1000);
        }

        return "test";
    }
}
