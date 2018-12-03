package com.example.japtxdemo.repository;

import com.example.japtxdemo.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lixinchao on 18-12-2
 */
public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByName(String name);
}
