package com.example.galloween2.repositories;

import com.example.galloween2.entities.TicketAirplane;
import com.example.galloween2.entities.projections.TicketAirplaneProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITicketAirplaneRepository extends JpaRepository<TicketAirplane, Long> {
    @Query(value = "SELECT * FROM tickets_airplanes " +
            "WHERE destination_id = :destinationId AND reservation_id IS NULL;", nativeQuery = true)
    List<TicketAirplaneProjection> findTicketsByDestination(Long destinationId);

    @Query(value = "SELECT cost FROM tickets_airplanes WHERE reservation_id = :id ;", nativeQuery = true)
    List<Long> findCostByReservation(Long id);

    @Query(value = "SELECT * FROM tickets_airplanes WHERE reservation_id = :id ;", nativeQuery = true)
    List<TicketAirplane> findByReservationId(Long id);
}
