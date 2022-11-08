package com.example.galloween2.services.interfaces;

import com.example.galloween2.controllers.dtos.request.CreateTicketBusRequest;
import com.example.galloween2.controllers.dtos.responses.CreateTicketBusResponse;

import java.util.List;

public interface ITicketBusService {
    CreateTicketBusResponse create(CreateTicketBusRequest request);

    List<CreateTicketBusResponse> get(Long idDestination);

    List<CreateTicketBusResponse> list();

    CreateTicketBusResponse update(Long id, Long reservation);

    void delete(Long id);
}
