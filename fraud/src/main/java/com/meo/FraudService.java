package com.meo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudService {
    private final FraudRepo fraudRepo;

    public Boolean isFraudulentCustomer(Integer customerTd, String msisdn){
        //toDO : is to call a wiremock with msisdn and return someData contains isFraduster Value
        fraudRepo.save(FraudCheckHistory.builder()
                .customerId(customerTd)
                .msisdn(msisdn)
                .isFrauder(false)  //toDo is to set the value coming from calling wiremock
                .createdAt(LocalDateTime.now())
                .build());
        return false;  //toDo is to return the value of isFrauder
    }
}
