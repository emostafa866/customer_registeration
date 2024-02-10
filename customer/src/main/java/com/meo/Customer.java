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
    @SequenceGenerator(name = "customer_id_seq",sequenceName = "customer_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "customer_id_seq")
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
