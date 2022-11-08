package com.example.galloween2.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CreateReservationResponse {
    private Long id;
    private String reservationDate;
    private Long cost;
    private List<Long> ticketAirplanes;
    private List<Long> ticketBuses;
    private List<Long> ticketCruiceShips;
    /*private Long client;*/
}
