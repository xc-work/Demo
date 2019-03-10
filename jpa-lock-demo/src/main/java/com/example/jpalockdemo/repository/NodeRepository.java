package com.example.jpalockdemo.repository;

import com.example.jpalockdemo.entity.Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Francis on 2019/3/9.
 */
@Repository
public interface NodeRepository extends CrudRepository<Node,Long> {
    Node findOrderById(long id);
}
