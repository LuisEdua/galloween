package com.example.galloween2.services.interfaces;

import com.example.galloween2.controllers.dtos.request.CreateTicketAirplaneRequest;
import com.example.galloween2.controllers.dtos.responses.CreateTicketAirplaneResponse;

import java.util.List;

public interface ITicketAirplaneService {
    CreateTicketAirplaneResponse create(CreateTicketAirplaneRequest request);

    List<CreateTicketAirplaneResponse> get(Long id);

    List<CreateTicketAirplaneResponse> list();

    CreateTicketAirplaneResponse update(Long id, Long reservationId);

    void delete(Long id);

    List<Long> getCost(Long id);
}
