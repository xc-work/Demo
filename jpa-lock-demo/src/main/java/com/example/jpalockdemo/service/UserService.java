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

import java.util.List;


/**
 * Created by lixinchao on 18-12-2
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public void add() {
        User user = userRepository.findUserByName("tom");
        user.setAge(user.getAge() + 1);
        userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addMore() {
        List<User> users = userRepository.findUserByGender("male");
        for (User user:users){
            user.setAge(user.getAge() + 1);
            userRepository.save(user);
        }
    }

}
