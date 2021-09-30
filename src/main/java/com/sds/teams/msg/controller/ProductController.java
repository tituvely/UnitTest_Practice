package com.sds.teams.msg.controller;

import com.sds.teams.msg.domain.Customer;
import com.sds.teams.msg.domain.Product;
import com.sds.teams.msg.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    /*
    GET /products : 상품 리스트 조회 API
    - 상품이름 검색 기능 포함(%like%)
    /products?name=a
    abc
    bac
    aaa
    */
    @GetMapping("/products")
    public List<Product> getProducts(@RequestParam(required = false) String name) {
        if(name == null) {
            //전체리스트 조회
            return productService.getAllProducts();
        } else {
            //이름 조회
            return productService.getProductsByName(name);
        }
    }

}
