package com.example.galloween2.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateReservationStatusRequest {
    private String status;

    private Long payment;
}
