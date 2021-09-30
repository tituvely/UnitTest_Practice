package com.sds.teams.msg.service;

import com.sds.teams.msg.domain.Product;
import com.sds.teams.msg.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public List<Product> getProductsByName(String name) {
        return productRepository.findAllByNameContains(name);
    }
}
