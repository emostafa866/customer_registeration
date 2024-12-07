package com.meo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meo.Customer;
import com.meo.CustomerRegisterationReq;
import com.meo.CustomerService;
import org.junit.jupiter.api.DisplayName;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@AutoConfigureMockMvc
@DisplayName("Controller | Integration Test")
@SpringBootTest
@ActiveProfiles("test")
public class CustomerControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

@Test
@DisplayName("success || customer register")
    public void registerCustomerSuccess() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        CustomerRegisterationReq request = new CustomerRegisterationReq(
                "mustafa",
                "emad",
                "mustafa@example.com",
                "01010101010",
                "12345"
        );
        doNothing().when(customerService).registerCustomer(any(CustomerRegisterationReq.class));

        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(customerService, times(1)).registerCustomer(any(CustomerRegisterationReq.class));

    }

    @Test
    @DisplayName("failure || duplicate customer register")
    public void registerDuplicateCustomer() throws Exception {
        CustomerRegisterationReq request = new CustomerRegisterationReq(
                "mustafa",
                "emad",
                "mustafa@example.com",
                "01010101010",
                "12345"
        );

        doThrow(new IllegalArgumentException("Customer already exists"))
                .when(customerService)
                .registerCustomer(any(CustomerRegisterationReq.class));

        mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isConflict()); // Expect 409 Conflict
    }

}
