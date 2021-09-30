package com.sds.teams.msg.controller;

import com.sds.teams.BaseUnitTest;
import com.sds.teams.msg.domain.Customer;
import com.sds.teams.msg.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

class CustomerControllerTest extends BaseUnitTest {

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerService mockCustomerService;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void POST_customers_callSaveCustomer_thenCallCustomerServiceSave() {
        Customer customer = new Customer("milo", "kim");
        customerController.saveCustomer(customer);

        then(mockCustomerService).should().save(customer);
        // or then(mockCustomerService).should(times(1)).save(customer);
    }

    @Test
    public void POST_customers_callSaveCustomer_withNameIsJames_thenThrowRuntimeException() {
        Customer customer = new Customer("james", "kim");

        assertThatThrownBy(() -> customerController.saveCustomer(customer))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void GET_customers_callGetAllCustomers_thenReturnCustomerList() {
        customerController.getAllCustomers(null);
        then(mockCustomerService).should().getAllCustomers();
        then(mockCustomerService).should(never()).getCustomerByLastName("milo");
    }

    @Test
    public void GET_customers_callGetAllCustomers_withLastNameParam_thenReturn_filtered_CustomerList() {
        customerController.getAllCustomers("milo");
        then(mockCustomerService).should().getCustomerByLastName(stringArgumentCaptor.capture());
        then(mockCustomerService).should(never()).getAllCustomers();

        assertThat(stringArgumentCaptor.getValue()).isEqualTo("milo");
    }

    @Test
    public void GET_customers_callGetCustomerById_thenReturn_Customer() {
        when(mockCustomerService.getCustomerById(1L)).thenReturn(new Customer(1L, "kim", "milo"));

        Customer result = customerController.getCustomerById(1L);

        then(mockCustomerService).should().getCustomerById(1L);
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getFirstName()).isEqualTo("kim");
        assertThat(result.getLastName()).isEqualTo("milo");
    }
}
