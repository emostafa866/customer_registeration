package com.meo;

import com.clients.FraudClient;
import com.clients.FraudResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    public void registerCustomer(CustomerRegisterationReq requset) {

        Customer customer=Customer.builder()
                .firstName(requset.firstName())
                .lastName(requset.lastName())
                .email(requset.email())
                .build();

        customerRepo.saveAndFlush(customer);
        FraudResponse response=fraudClient.isFraudster(customer.getId());

        if(response.isFraud()) throw new IllegalStateException("fraud customer");
    }
}
