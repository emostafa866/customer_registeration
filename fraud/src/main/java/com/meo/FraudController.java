package com.meo;

import com.clients.FraudResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fraud")
public class FraudController {
    private final FraudService fraudService;
    @GetMapping(path = "{customerId}")
    public FraudResponse isFraudster(@PathVariable("customerId")Integer customerId){
       boolean result= fraudService.isFraudulentCustomer(customerId);
       return new FraudResponse(result);
    }
}
