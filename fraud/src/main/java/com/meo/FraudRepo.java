package com.meo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudRepo extends JpaRepository<FraudCheckHistory,Integer> {
}
