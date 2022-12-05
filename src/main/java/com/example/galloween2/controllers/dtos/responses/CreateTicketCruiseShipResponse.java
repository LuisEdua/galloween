package com.example.galloween2.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateTicketCruiseShipResponse {
    private Long id;
    private String departureDate;
    private Long seatNumber;
    private String classType;
    private String origin;
    private String checkInTime;
    private Long cost;
}

