package com.example.jpalockdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Francis on 2019/3/9.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int number;

    @Version
    private int ver;
}
