package com.sds.teams.msg.controller;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.sds.teams.BaseIntegrationTest;
import com.sds.teams.msg.domain.Customer;
import com.sds.teams.msg.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
        customerRepository.saveAll(
                Lists.newArrayList(
                        new Customer(1L, "kim", "milo"),
                        new Customer(2L, "park", "jun"),
                        new Customer(3L, "lee", "min"),
                        new Customer(4L, "park", "milo")
                )
        );
    }

    @Test
    public void POST_saveCustomer_thenSavedCustomerAndReturnData() throws Exception {
        customerRepository.deleteAll();

        Path reqPath = Paths.get(Resources.getResource("json/post_save_customer_req.json").toURI());

        assertThat(Lists.newArrayList(customerRepository.findAll()).size()).isEqualTo(0);
        mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON).content(Files.readAllBytes(reqPath)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is("kim")))
                .andExpect(jsonPath("$.lastName", is("milo"))
                );

        assertThat(Lists.newArrayList(customerRepository.findAll()).size()).isEqualTo(1);
    }

    @Test
    public void POST_saveCustomer_withNameJames_thenErrorResponse() throws Exception {
        Path reqPath = Paths.get(Resources.getResource("json/post_save_customer_error_req.json").toURI());

        mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON).content(Files.readAllBytes(reqPath)))
                .andExpect(status().is5xxServerError()
                );

    }

    @Test
    public void GET_getAllCustomers_thenReturnCustomers() throws Exception {

        mockMvc.perform(get("/customers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].lastName").value("milo"))
                .andExpect(jsonPath("$[1].lastName").value("jun"))
                .andExpect(jsonPath("$[2].lastName").value("min"))
                .andExpect(jsonPath("$[3].lastName").value("milo"));
    }

    @Test
    public void GET_getAllCustomers_withLastNameParam_thenReturnFilteredCustomers() throws Exception {
        mockMvc.perform(get("/customers").param("lastName", "milo").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName").value("kim"))
                .andExpect(jsonPath("$[0].lastName").value("milo"))
                .andExpect(jsonPath("$[1].firstName").value("park"))
                .andExpect(jsonPath("$[1].lastName").value("milo"));
    }
}
