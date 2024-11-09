package com.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "customer",url = "http://localhost:8080")
public interface CustomerClient {
    @GetMapping(path = "api/v1/customers/{email}")
    Customer getCustomerByUsername(@PathVariable("email") String email);
}