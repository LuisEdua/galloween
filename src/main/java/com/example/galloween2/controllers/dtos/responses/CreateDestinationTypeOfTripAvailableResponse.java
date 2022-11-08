package com.example.galloween2.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDestinationTypeOfTripAvailableResponse {
    private Long id;
    private Long destination;
    private Long typeOfTrip;
}
