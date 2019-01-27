package com.example.jpalockdemo.service;

import com.example.jpalockdemo.entity.User;
import com.example.jpalockdemo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


/**
 * Created by lixinchao on 18-12-2
 */
@Slf4j
@Service
public class UserService  {


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    public void test(){
        userRepository.save(User.builder().name("aa").gender("male").age(18).build());
        userService.insertForTx2();
//        insertForTx3();
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean insertForTx1() {

        userRepository.save(User.builder().name("Tom").gender("male").age(18).build());
        userRepository.save(User.builder().name("Lily").gender("female").age(17).build());

        return true;

    }

    @Transactional(rollbackFor = Exception.class)
    public boolean insertForTx2() {

        userRepository.save(User.builder().name("Tom").gender("male").age(18).build());

        try {
            int i = 1/0;
            userRepository.save(User.builder().name("Lily").gender("female").age(17).build());
        }catch (Exception e){
            log.info("Save Failed ---> Transaction Rollback");
            throw e;
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean insertForTx3() {

        userRepository.save(User.builder().name("Tom").gender("male").age(18).build());

        try {
            int i = 1/0;
            userRepository.save(User.builder().name("Lily").gender("female").age(17).build());
        }catch (Exception e){
            log.info("Save Failed ---> Transaction Rollback");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
        return true;
    }

}
