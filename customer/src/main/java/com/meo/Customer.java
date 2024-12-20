package com.meo;


import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_id_seq",sequenceName = "customer_id_seq",allocationSize = 1 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_id_seq")
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String msisdn;
    private String password;

}
