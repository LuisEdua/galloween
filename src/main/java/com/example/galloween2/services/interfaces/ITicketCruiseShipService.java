package com.example.galloween2.services.interfaces;

import com.example.galloween2.controllers.dtos.request.CreateTicketCruiseShipRequest;
import com.example.galloween2.controllers.dtos.responses.CreateTicketCruiseShipResponse;

import java.util.List;

public interface ITicketCruiseShipService {
    CreateTicketCruiseShipResponse create(CreateTicketCruiseShipRequest request);

    List<CreateTicketCruiseShipResponse> get(Long id);

    List<CreateTicketCruiseShipResponse> list();

    CreateTicketCruiseShipResponse update(Long id, Long idReservation);

    void delete(Long id);

    List<Long> getCost(Long id);
}
