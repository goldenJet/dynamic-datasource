package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Jet.chen on 2017/9/4.
 */
@Data
@Entity(name = "usr")
public class User {
    @Id
    private Long id;

    private String name;

    private String address;
}
