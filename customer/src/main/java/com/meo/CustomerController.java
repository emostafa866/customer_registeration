package com.meo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {
    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegisterationReq requset){
        log.info("new customer regesteration"+ requset);
        customerService.registerCustomer(requset);
    }

    @GetMapping(path = "{email}")
    public Customer getCustomerByUsername(@PathVariable String email){
        return customerService.findCustomerByEmail(email);
    }
}
