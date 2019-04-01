package com.example.java8.service.impl;

import com.example.java8.entity.Product;
import com.example.java8.entity.User;
import com.example.java8.repository.ProductRepository;
import com.example.java8.repository.UserRepository;
import com.example.java8.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public boolean saveProduct(Product product) {
        if (!productRepository.existsByName(product.getName())){
            productRepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        if (productRepository.existsById(id)){
            productRepository.findById(id).ifPresent(productRepository::delete);
            return true;
        }
        return false;
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getByName(String name) {
        return productRepository.findAll().stream().filter(product -> product.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public List<Product> findByUserId(int id) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
            return null;
        }
        User user = byId.get();
        return productRepository.findAllByUser(user);

    }
}