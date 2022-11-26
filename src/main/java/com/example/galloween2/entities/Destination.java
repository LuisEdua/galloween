package com.example.galloween2.entities;

import com.example.galloween2.entities.pivots.DestinationTypeOfTripAvailable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String state;

    private String country;

    private String  photography;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<TicketCruiseShip> ticketCruiseShips;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<TicketAirplane> ticketAirplanes;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<TicketBus> ticketBuses;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<DestinationTypeOfTripAvailable> destinationTypeOfTripAvailables;
}
