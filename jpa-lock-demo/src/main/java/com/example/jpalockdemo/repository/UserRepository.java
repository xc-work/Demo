package com.example.jpalockdemo.repository;

import com.example.jpalockdemo.entity.User;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * Created by lixinchao on 18-12-2
 */

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    User findUserByName(String name);

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<User> findUserByGender(String gender);
}
