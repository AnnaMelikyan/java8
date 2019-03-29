package com.example.java8.service.impl;

import com.example.java8.entity.Product;
import com.example.java8.repository.ProductRepository;
import com.example.java8.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
            Optional<Product> byId = productRepository.findById(id);
            if (byId.isPresent()){
                productRepository.delete(byId.get());
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Product> getProductById(int id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()){
            return byId;
        }
        return null;
    }
}
