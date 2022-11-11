package com.example.galloween2.repositories;

import com.example.galloween2.entities.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservationStatusRepository extends JpaRepository<ReservationStatus, Long> {

    @Query(value = "SELECT * FROM reservations_status WHERE reservation = :id ;", nativeQuery = true)
    ReservationStatus findByReservationId(Long id);

    @Query(value = "SELECT * FROM reservations_status WHERE payment_id = :id ;", nativeQuery = true)
    List<ReservationStatus> findByPaymentId(Long id);

}
