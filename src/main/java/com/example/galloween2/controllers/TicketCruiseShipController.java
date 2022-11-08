package com.example.galloween2.controllers;

import com.example.galloween2.controllers.dtos.request.CreateTicketCruiseShipRequest;
import com.example.galloween2.controllers.dtos.responses.CreateTicketCruiseShipResponse;
import com.example.galloween2.services.interfaces.ITicketCruiseShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ticket-cruise-ship")
public class TicketCruiseShipController {
    @Autowired
    private ITicketCruiseShipService service;


    @PostMapping
    public CreateTicketCruiseShipResponse create(@RequestBody CreateTicketCruiseShipRequest request) {
        return service.create(request);
    }

    @GetMapping("{id}")
    public List<CreateTicketCruiseShipResponse> get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public List<CreateTicketCruiseShipResponse> list() {
        return service.list();
    }

    @PutMapping("{id}/{idReservation}")
    public CreateTicketCruiseShipResponse update(@PathVariable Long id, @PathVariable Long idReservation) {
        return service.update(id, idReservation);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

