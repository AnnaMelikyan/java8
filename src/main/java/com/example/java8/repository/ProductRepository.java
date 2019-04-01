package com.example.java8.repository;

import com.example.java8.entity.Product;
import com.example.java8.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByName(String name);

    List<Product> findAllByUser(User user);
}
