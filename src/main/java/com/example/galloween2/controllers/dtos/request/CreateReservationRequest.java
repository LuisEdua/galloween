package com.example.galloween2.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateReservationRequest {
    private String reservationDate;
    private String destination;
    private String typeOfTrip;
}
