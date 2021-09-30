package com.sds.teams.msg.repository;

import com.sds.teams.msg.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByNameContains(String name);
}