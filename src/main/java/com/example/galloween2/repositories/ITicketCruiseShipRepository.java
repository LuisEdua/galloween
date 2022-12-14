package com.example.galloween2.repositories;

import com.example.galloween2.entities.TicketAirplane;
import com.example.galloween2.entities.TicketCruiseShip;
import com.example.galloween2.entities.projections.TicketAirplaneProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITicketCruiseShipRepository extends JpaRepository<TicketCruiseShip, Long> {
    @Query(value = "SELECT * FROM tickets_cruises_ships " +
            "WHERE destination_id = :destinationId AND reservation_id IS NULL;", nativeQuery = true)
    List<TicketAirplaneProjection> findTicketsByDestination(Long destinationId);

    @Query(value = "SELECT cost FROM tickets_cruises_ships WHERE reservation_id = :id ;", nativeQuery = true)
    List<Long> findCostByReservationId(Long id);

    @Query(value = "SELECT * FROM tickets_cruises_ships WHERE reservation_id = :id ;", nativeQuery = true)
    List<TicketCruiseShip> findByReservationId(Long id);
}
