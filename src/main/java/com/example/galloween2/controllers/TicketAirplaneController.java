package com.example.galloween2.controllers;

import com.example.galloween2.controllers.dtos.request.CreateTicketAirplaneRequest;
import com.example.galloween2.controllers.dtos.responses.CreateTicketAirplaneResponse;
import com.example.galloween2.services.interfaces.ITicketAirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ticket-airplane")
public class TicketAirplaneController {
    @Autowired
    private ITicketAirplaneService service;


    @PostMapping
    public CreateTicketAirplaneResponse create(@RequestBody CreateTicketAirplaneRequest request) {
        return service.create(request);
    }

    @GetMapping("{destinationId}")
    public List<CreateTicketAirplaneResponse> get(@PathVariable Long destinationId) {
        return service.get(destinationId);
    }

    @GetMapping
    public List<CreateTicketAirplaneResponse> list() {
        return service.list();
    }

    @PutMapping("{id}/{reservationId}")
    public CreateTicketAirplaneResponse update(@PathVariable Long id, @PathVariable Long reservationId) {
        return service.update(id, reservationId);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

