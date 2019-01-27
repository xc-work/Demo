package com.example.jpalockdemo.generator;

import com.example.jpalockdemo.entity.User;
import com.example.jpalockdemo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Francis on 2019/1/27.
 */
@Component
public class UserGenerator implements CommandLineRunner {

    UserRepository userRepository;

    @Override
    public void run(String... strings) throws Exception {
//        for (int i=0;i<100;i++){
//            User user = User.builder().name(String.valueOf(i)).build();
//            userRepository.save(user);
//        }
    }
}
