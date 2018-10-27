package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Administrator on 2018/10/17.
 */

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private Integer id;


    // 添加一个用户
    @PostMapping(value="user")
    public User userAdd(@RequestParam("name") String name, @RequestParam("gender")String gender){
        User user = new User();
        user.setName(name);
        user.setGender(gender);
        return userRepository.save(user);
    }

    // 查询一个用户
    @GetMapping(value="/user/{id}")
    public Optional<User> userFineOne(@PathVariable("id") Integer id){
        return userRepository.findById(id);
    }

    // 更新一个用户
    @PutMapping(value="/user/{id}")
    public User userUpdate(@PathVariable("id") Integer id,
                           @RequestParam("name") String name,@RequestParam("gender")String gender){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setGender(gender);
        return userRepository.save(user);
    }

    // 删除一个用户
    @DeleteMapping(value="/user/{id}")
    public void userUpdate(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
    }

    //保存用户后立刻查询用户
    @GetMapping("/userSaveAndRead/{name}/{gender}")
    public User userSaveAndRead(@PathVariable String name,@PathVariable String gender) {
        User user = new User();
        user.setName(name);
        user.setGender(gender);
        userRepository.saveAndFlush(user);
        return userRepository.getOne(user.getId());
    }

}
