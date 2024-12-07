package com.meo.services;

import com.clients.FraudClient;
import com.clients.FraudResponse;
import com.meo.Customer;
import com.meo.CustomerRegisterationReq;
import com.meo.CustomerRepo;
import com.meo.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepo customerRepo;

    @Mock
    private FraudClient fraudClient;
    @InjectMocks
    private CustomerService customerService;

    private CustomerRegisterationReq request;

    @BeforeEach
    void setup() {
        request = new CustomerRegisterationReq(
                "Mustafa",
                "Emad",
                "mustafa@example.com",
                "01010101010",
                "12345"
        );

    }
    @Test
    @DisplayName("Success | register")
    void registerCustomerSuccessfully() {

        Mockito.when(customerRepo.findByEmail(request.email())).thenReturn(null);
        Mockito.when(fraudClient.isFraudster(Mockito.anyObject(),Mockito.anyString())).thenReturn(new FraudResponse(false));

        customerService.registerCustomer(request);

        Mockito.verify(customerRepo, Mockito.times(1))
                .saveAndFlush(Mockito.any(Customer.class));
        Mockito.verify(fraudClient, Mockito.times(1))
                .isFraudster(Mockito.anyObject(), Mockito.eq(request.msisdn()));
    }
}
