package com.example.galloween2.repositories;

import com.example.galloween2.entities.TypeOfTripAvailable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeOfTripAvailableRepository extends JpaRepository <TypeOfTripAvailable, Long> {
}
