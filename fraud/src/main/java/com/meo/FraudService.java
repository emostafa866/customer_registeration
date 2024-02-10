package com.meo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudService {
    private final FraudRepo fraudRepo;

    public Boolean isFraudulentCustomer(Integer customerTd){

        fraudRepo.save(FraudCheckHistory.builder()
                .customerId(customerTd)
                .isFrauder(false)
                .createdAt(LocalDateTime.now())
                .build());
        return false;
    }
}
