package com.example.java8.controller;


import com.example.java8.entity.User;
import com.example.java8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController( UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/findAll")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> collect = userService.findAll().stream().collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(collect);
  }

  @GetMapping("/findById")
  public ResponseEntity<User> getUserById(int id) {
    User userById = userService.getUserById(id);
    if (userById == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(userById);
  }

  @PostMapping("/saveUser")
  public ResponseEntity<Void> saveUser(User user) {
    userService.saveUser(user);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/deleteById")
  public ResponseEntity<Void> deleteById(int id) {
    if (userService.deleteUser(id)) {
      return ResponseEntity.status(HttpStatus.OK).build();
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }

  @GetMapping("/findByName")
  public ResponseEntity<List<User>> findByName(String name){
    return ResponseEntity.status(HttpStatus.OK).body(userService.getByName(name));
  }

}