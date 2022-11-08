package com.example.galloween2.repositories;

import com.example.galloween2.entities.TicketAirplane;
import com.example.galloween2.entities.projections.TicketAirplaneProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITicketAirplaneRepository extends JpaRepository<TicketAirplane, Long> {
    @Query(value = "select * from tickets_airplanes " +
            "where destination_id = :destinationId and reservation_id is null;", nativeQuery = true)
    List<TicketAirplaneProjection> findTicketsByDestination(Long destinationId);
}
