package com.example.java8.service.impl;

import com.example.java8.entity.User;
import com.example.java8.repository.UserRepository;
import com.example.java8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
            userRepository.findById(id).ifPresent(userRepository::delete);
            return true;
        }
        return false;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);

    }

    @Override
    public List<User> getByName(String name) {
        return userRepository.findAll().stream().filter(product -> product.getName().equals(name)).collect(Collectors.toList());

    }
}