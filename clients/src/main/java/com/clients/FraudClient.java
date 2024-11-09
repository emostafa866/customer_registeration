package com.clients;

import liquibase.pro.packaged.S;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "fraud", url = "http://localhost:8081")
public interface FraudClient {
    @GetMapping(path = "api/v1/fraud/{customerId}/{msisdn}")
     FraudResponse isFraudster(@PathVariable("customerId")Integer customerId,@PathVariable("msisdn") String msisdn);
}
