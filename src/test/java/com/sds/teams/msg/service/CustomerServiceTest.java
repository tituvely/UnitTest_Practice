package com.sds.teams.msg.service;

import com.google.common.collect.Lists;
import com.sds.teams.BaseUnitTest;
import com.sds.teams.msg.domain.Customer;
import com.sds.teams.msg.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

class CustomerServiceTest extends BaseUnitTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository mockCustomerRepository;

    @Test
    public void whenCallSave_thenCallRepositorySave() {
        Customer customer = new Customer("milo", "kim");
        customerService.save(customer);
        then(mockCustomerRepository).should().save(customer);
    }

    @Test
    public void whenCallGetAllCustomers_thenReturnCustomersList() {
        List<Customer> customers = Lists.newArrayList(new Customer("milo", "kim"), new Customer("james", "lee"));
        when(mockCustomerRepository.findAll()).thenReturn(customers);

        List<Customer> allCustomers = customerService.getAllCustomers();

        then(mockCustomerRepository).should().findAll();

        assertThat(allCustomers.size()).isEqualTo(2);
        assertThat(allCustomers).contains(new Customer("milo", "kim"), new Customer("james", "lee"));
    }

    @Test
    public void whenCallGetCustomerById_thenReturnCustomer() {
        when(mockCustomerRepository.findById(1L)).thenReturn(
                Optional.of(new Customer(1L, "milo", "kim"))
        );

        Customer customer = customerService.getCustomerById(1L);

        then(mockCustomerRepository).should().findById(1L);

        assertThat(customer.getFirstName()).isEqualTo("milo");
        assertThat(customer.getLastName()).isEqualTo("kim");
    }
}
