package com.example.galloween2.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateTicketCruiseShipRequest {
    private String departureDate;
    private Long seatNumber;
    private String classType;
    private String origin;
    private String checkInTime;
    private Long cost;
    private Long destination;
}
