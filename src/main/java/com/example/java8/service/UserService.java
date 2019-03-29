package com.example.java8.service;

import com.example.java8.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> findAll();

    boolean saveUser(User user);

    boolean deleteUser(int id);

    Optional<User> getUserById(int id);

}
