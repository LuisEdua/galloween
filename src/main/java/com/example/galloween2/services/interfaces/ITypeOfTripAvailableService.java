package com.example.galloween2.services.interfaces;

import com.example.galloween2.controllers.dtos.request.CreateTypeOfTripAvailableRequest;
import com.example.galloween2.controllers.dtos.responses.CreateTypeOfTripAvailableResponse;

import java.util.List;

public interface ITypeOfTripAvailableService {
    CreateTypeOfTripAvailableResponse create(CreateTypeOfTripAvailableRequest request);

    CreateTypeOfTripAvailableResponse get(Long id);

    List<CreateTypeOfTripAvailableResponse> list();

    CreateTypeOfTripAvailableResponse update(Long id, CreateTypeOfTripAvailableRequest request);

    void delete(Long id);
}
