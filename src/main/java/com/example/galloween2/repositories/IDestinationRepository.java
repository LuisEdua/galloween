package com.example.galloween2.repositories;

import com.example.galloween2.entities.Destination;
import com.example.galloween2.entities.projections.DestinationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDestinationRepository extends JpaRepository<Destination, Long> {
}
