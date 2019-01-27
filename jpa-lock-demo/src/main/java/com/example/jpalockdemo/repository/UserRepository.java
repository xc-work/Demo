package com.example.jpalockdemo.repository;

import com.example.jpalockdemo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lixinchao on 18-12-2
 */

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByName(String name);
}
