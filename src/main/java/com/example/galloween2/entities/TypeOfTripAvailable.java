package com.example.galloween2.entities;

import com.example.galloween2.entities.pivots.DestinationTypeOfTripAvailable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="types_of_trips_availables")
public class TypeOfTripAvailable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeOfTrip;

    /*@OneToMany(mappedBy="typeOfTrip")
    private List<Ticket> tickets;*/

    @OneToMany(mappedBy = "typeOfTripAvailable")
    private List<DestinationTypeOfTripAvailable> destinationTypeOfTripAvailables;
}
