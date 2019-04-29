package com.crystal.spring.lesson_1.test.service;

import com.crystal.spring.lesson_1.annotation.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    public String getUser(String name) {
        return "My name is " + name;
    }
}
