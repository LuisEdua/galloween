package com.example.galloween2.repositories;

import com.example.galloween2.entities.pivots.DestinationTypeOfTripAvailable;
import com.example.galloween2.entities.projections.DestinationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDestinationTypeOfTripAvailableRepository extends JpaRepository<DestinationTypeOfTripAvailable, Long> {
    @Query(value = "select destinations.* from destination_type_of_trip_availables " +
            "inner join destinations on destinations.id=destination_type_of_trip_availables.destination_id " +
            "inner join types_of_trips_availables on types_of_trips_availables.id=destination_type_of_trip_availables.type_of_trip_available_id "+
            "where types_of_trips_availables.type_of_trip = :typeOfTrip ;", nativeQuery = true)
    List<DestinationProjection> desinationsList(String typeOfTrip);
}
