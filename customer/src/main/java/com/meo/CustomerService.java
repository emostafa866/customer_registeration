package com.meo;

import com.clients.FraudClient;
import com.clients.FraudResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final FraudClient fraudClient;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerCustomer(CustomerRegisterationReq requset) {

        Customer customer=Customer.builder()
                .firstName(requset.firstName())
                .lastName(requset.lastName())
                .email(requset.email())
                .msisdn(requset.msisdn())
                .password(passwordEncoder.encode(requset.password()))
                .build();

        customerRepo.saveAndFlush(customer);
        FraudResponse response=fraudClient.isFraudster(customer.getId(), customer.getMsisdn());

        if(response.isFraud()) throw new IllegalStateException("fraud customer");
    }

    public Customer findCustomerByEmail(String email) {
        return customerRepo.findByEmail(email).orElse(null);
    }
}
