package com.example.java8.service;

import com.example.java8.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> findAll();

    boolean saveUser(User user);

    boolean deleteUser(int id);

    User getUserById(int id);

    List<User> getByName(String name);
}