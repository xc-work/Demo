package com.example.jpatxdemo.repository;

import com.example.jpatxdemo.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lixinchao on 18-12-2
 */
public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByName(String name);
}
