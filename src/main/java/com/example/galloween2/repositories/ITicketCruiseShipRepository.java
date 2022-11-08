package com.example.galloween2.repositories;

import com.example.galloween2.entities.TicketCruiseShip;
import com.example.galloween2.entities.projections.TicketAirplaneProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITicketCruiseShipRepository extends JpaRepository<TicketCruiseShip, Long> {
    @Query(value = "select * from tickets_cruises_ships " +
            "where destination_id = :destinationId and reservation_id is null;", nativeQuery = true)
    List<TicketAirplaneProjection> findTicketsByDestination(Long destinationId);
}
