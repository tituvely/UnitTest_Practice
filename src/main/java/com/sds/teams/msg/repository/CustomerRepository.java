package com.sds.teams.msg.repository;

import java.util.List;

import com.sds.teams.msg.domain.Customer;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer, Long>{
    List<Customer> findByLastName(String lastName);
}
