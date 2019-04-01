package com.example.java8.service;

import com.example.java8.entity.Product;
import com.example.java8.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> findAll();

    boolean saveProduct(Product Product);

    boolean deleteProduct(int id);

    Product getProductById(int id);

    List<Product> getByName(String name);

    List<Product> findByUserId(int id);
}