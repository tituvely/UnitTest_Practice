package com.sds.teams.msg.controller;

import com.sds.teams.msg.domain.Customer;
import com.sds.teams.msg.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer) {
        if ("james".equals(customer.getFirstName())) {
            throw new RuntimeException();
        }
        return customerService.save(customer);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/customers")
    public List<Customer> getAllCustomers(@RequestParam(value = "lastName", required = false) String lastName) {
        if (lastName != null) {
            return customerService.getCustomerByLastName(lastName);
        } else {
            return customerService.getAllCustomers();
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id) {
        return customerService.getCustomerById(id);
    }
}
