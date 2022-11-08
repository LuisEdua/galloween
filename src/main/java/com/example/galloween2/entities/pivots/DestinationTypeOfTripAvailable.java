package com.example.galloween2.entities.pivots;

import com.example.galloween2.entities.Destination;
import com.example.galloween2.entities.TypeOfTripAvailable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="destination_type_of_trip_availables")
public class DestinationTypeOfTripAvailable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Destination destination;

    @ManyToOne
    private TypeOfTripAvailable typeOfTripAvailable;
}
