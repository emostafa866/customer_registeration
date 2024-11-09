package com.meo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudService {
    private final FraudRepo fraudRepo;

    public Boolean isFraudulentCustomer(Integer customerTd, String msisdn){

        fraudRepo.save(FraudCheckHistory.builder()
                .customerId(customerTd)
                .msisdn(msisdn)
                .isFrauder(false)
                .createdAt(LocalDateTime.now())
                .build());
        return false;
    }
}
