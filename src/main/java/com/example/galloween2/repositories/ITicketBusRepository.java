package com.example.galloween2.repositories;

import com.example.galloween2.entities.TicketBus;
import com.example.galloween2.entities.projections.TicketBusProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITicketBusRepository extends JpaRepository<TicketBus, Long> {
    @Query(value = "select * from tickets_buses " +
    "where destination_id = :destinationId and reservation_id is null;", nativeQuery = true)
    List<TicketBusProjection> findTicketsByDestination(Long destinationId);

}
