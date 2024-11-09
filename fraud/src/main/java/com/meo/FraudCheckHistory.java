package com.meo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Builder
@Table(name = "fraud")
public class FraudCheckHistory {
    @Id
    @SequenceGenerator(name = "fraud_id_seq",sequenceName = "fraud_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "fraud_id_seq")
    private Integer id;
    private Integer customerId;
    private Boolean isFrauder;
    private LocalDateTime createdAt;
    private String msisdn;
}
