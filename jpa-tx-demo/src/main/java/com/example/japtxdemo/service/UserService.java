package com.example.japtxdemo.service;

import com.example.japtxdemo.entity.User;
import com.example.japtxdemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


/**
 * Created by lixinchao on 18-12-2
 */
@Component
public class UserService implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Transactional(rollbackFor = Exception.class)
    public boolean insertForTx1() {

        userRepository.save(User.builder().name("Tom").gender("male").age(18).build());
        int i = 1/0;
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

            logger.info("Save Failed ---> Transaction Rollback");
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

            logger.info("Save Failed ---> Transaction Rollback");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;

        }

        return true;

    }



    @Override
    public void run(String... args) throws Exception {

        logger.info(" User Service Started !!! \n");

//        boolean b1 = userService.insertForTx1();
//        System.out.println(b1);

        boolean b2 = userService.insertForTx2();
        System.out.println(b2);

        boolean b3 = userService.insertForTx3();
        System.out.println(b3);

    }
}
