package com.example.galloween2.services.interfaces;

import com.example.galloween2.controllers.dtos.request.CreateTypeOfTripAvailableRequest;
import com.example.galloween2.controllers.dtos.responses.CreateTypeOfTripAvailableResponse;
import com.example.galloween2.entities.TypeOfTripAvailable;

import java.util.List;

public interface ITypeOfTripAvailableService {
    CreateTypeOfTripAvailableResponse create(CreateTypeOfTripAvailableRequest request);

    CreateTypeOfTripAvailableResponse get(Long id);

    List<CreateTypeOfTripAvailableResponse> list();

    CreateTypeOfTripAvailableResponse update(Long id, CreateTypeOfTripAvailableRequest request);

    TypeOfTripAvailable findById(Long id);

    void delete(Long id);
}
