package com.meo;

import com.clients.Customer;
import com.clients.CustomerClient;
import com.clients.FraudResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fraud")
public class FraudController {
    private final FraudService fraudService;
    private final CustomerClient customerClient;

    @GetMapping(path = "{customerId}/{msisdn}")
    public FraudResponse isFraudster(@PathVariable("customerId")Integer customerId, @PathVariable("msisdn") String msisdn){
       boolean result= fraudService.isFraudulentCustomer(customerId , msisdn);
       return new FraudResponse(result);
    }

    @GetMapping( "/email/{email}")
    public Customer getCustomer(@PathVariable String email){
       return customerClient.getCustomerByUsername(email);
    }
}
