package com.example.galloween2.repositories;

import com.example.galloween2.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservations WHERE user_id = :userId ;", nativeQuery = true)
    List<Reservation> findReservationByUserId(Long userId);

    @Query(value = "UPDATE reservations SET user_id = :user where user_id = :id ;", nativeQuery = true)
    List<Reservation> userNull(Long id, Long user);

}
