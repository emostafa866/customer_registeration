package com.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "fraud")
public interface FraudClient {
    @GetMapping(path = "api/v1/fraud/{customerId}")
     FraudResponse isFraudster(@PathVariable("customerId")Integer customerId);
}
