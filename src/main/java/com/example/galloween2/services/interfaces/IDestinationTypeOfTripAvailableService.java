package com.example.galloween2.services.interfaces;

import com.example.galloween2.controllers.dtos.request.CreateDestinationTypeOfTripAvailableRequest;
import com.example.galloween2.controllers.dtos.responses.CreateDestinationTypeOfTripAvailableResponse;
import com.example.galloween2.controllers.dtos.responses.DestinationTypeOfTripAvailableResponse;

import java.util.List;

public interface IDestinationTypeOfTripAvailableService {
    CreateDestinationTypeOfTripAvailableResponse create(CreateDestinationTypeOfTripAvailableRequest request);

    List<DestinationTypeOfTripAvailableResponse> get(String typeOfTrip);

    List<CreateDestinationTypeOfTripAvailableResponse> list();

    CreateDestinationTypeOfTripAvailableResponse update(Long id, CreateDestinationTypeOfTripAvailableRequest request);

    void delete(Long id);
}
