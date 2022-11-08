package com.example.galloween2.repositories;

import com.example.galloween2.entities.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationStatusRepository extends JpaRepository<ReservationStatus, Long> {

}
