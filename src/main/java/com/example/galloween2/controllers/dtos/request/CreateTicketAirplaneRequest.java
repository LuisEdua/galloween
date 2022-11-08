package com.example.galloween2.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateTicketAirplaneRequest {
    private String departureDate;
    private String checkInTime;
    private Long seatNumber;
    private String class_type;
    private String origin;
    private Long cost;
    private Long Destination;
}
