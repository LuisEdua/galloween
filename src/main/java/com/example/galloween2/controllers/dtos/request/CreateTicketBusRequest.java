package com.example.galloween2.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateTicketBusRequest {
    private String departureDate;
    private Long seatNumber;
    private String class_type;
    private String origin;
    private String checkInTime;
    private Long destination;
    private Long cost;
}
