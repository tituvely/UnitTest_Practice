package com.sds.teams.msg.service;

import com.google.common.collect.Lists;
import com.sds.teams.msg.domain.Customer;
import com.sds.teams.msg.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return Lists.newArrayList(repository.findAll());
    }

    public Customer getCustomerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Customer> getCustomerByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

}