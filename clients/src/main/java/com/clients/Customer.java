package com.clients;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String msisdn;
    private String password;
    private String username;
}
