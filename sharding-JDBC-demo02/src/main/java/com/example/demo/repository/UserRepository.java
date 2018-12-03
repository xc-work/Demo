package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2018/10/17.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
