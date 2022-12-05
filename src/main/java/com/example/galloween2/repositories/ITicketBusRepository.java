package com.example.galloween2.repositories;

import com.example.galloween2.entities.TicketAirplane;
import com.example.galloween2.entities.TicketBus;
import com.example.galloween2.entities.projections.TicketBusProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITicketBusRepository extends JpaRepository<TicketBus, Long> {
    @Query(value = "SELECT * FROM tickets_buses " +
    "WHERE destination_id = :destinationId AND reservation_id IS NULL;", nativeQuery = true)
    List<TicketBusProjection> findTicketsByDestination(Long destinationId);

    @Query(value = "SELECT cost FROM tickets_buses WHERE reservation_id = :id ;", nativeQuery = true)
    List<Long> findCostByReservationId(Long id);

    @Query(value = "SELECT * FROM tickets_buses WHERE reservation_id = :id ;", nativeQuery = true)
    List<TicketBus> findByReservationId(Long id);
}
