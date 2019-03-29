package com.example.java8.service.impl;

import com.example.java8.entity.User;
import com.example.java8.repository.UserRepository;
import com.example.java8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean saveUser(User user) {
        if (!userRepository.existsByEmail(user.getEmail())){
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        if (userRepository.existsById(id)){
            Optional<User> byId = userRepository.findById(id);
            if (byId.isPresent()){
                userRepository.delete(byId.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<User> getUserById(int id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()){
            return byId;
        }
        return null;
    }
}
