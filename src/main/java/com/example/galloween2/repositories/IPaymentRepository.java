package com.example.galloween2.repositories;

import com.example.galloween2.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT payment_type from payments where user_id = :userId ;", nativeQuery = true)
    List<Payment> findPaymentByUserId(Long userId);
}
