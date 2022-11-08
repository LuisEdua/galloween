package com.example.galloween2.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateDestinationTypeOfTripAvailableRequest {
    private Long destination;
    private Long typeOfTrip;
}
