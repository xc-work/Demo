package com.example.jpalockdemo.service;

import com.example.jpalockdemo.entity.Node;
import com.example.jpalockdemo.repository.NodeRepository;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Created by Francis on 2019/3/9.
 */
@Service
public class NodeService {
    @Autowired
    private NodeRepository nodeRepository;

//    @Synchronized
//    @Transactional(rollbackFor = Exception.class)
//    @Retryable(value = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public boolean add() {
        try {
            Node node = nodeRepository.findOrderById(1L);
            node.setNumber(node.getNumber() + 1);
            nodeRepository.save(node);
            return true;
        } catch (Exception e) {
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("-----------------------------------------------");
            return false;
        }
    }

}
