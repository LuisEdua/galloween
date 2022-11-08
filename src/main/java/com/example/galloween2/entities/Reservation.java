package com.example.galloween2.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reservationDate;

    private Long cost;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(mappedBy = "reservation")
    private ReservationStatus reservationStatus;

    @OneToMany(mappedBy = "reservation")
    private List<TicketCruiseShip> ticketCruiseShips;

    @OneToMany(mappedBy = "reservation")
    private List<TicketAirplane> ticketAirplanes;

    @OneToMany(mappedBy = "reservation")
    private List<TicketBus> ticketBuses;
}
