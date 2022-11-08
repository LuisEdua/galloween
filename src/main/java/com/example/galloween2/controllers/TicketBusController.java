package com.example.galloween2.controllers;

import com.example.galloween2.controllers.dtos.request.CreateTicketBusRequest;
import com.example.galloween2.controllers.dtos.responses.CreateTicketBusResponse;
import com.example.galloween2.services.interfaces.ITicketBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("ticket-bus")
public class TicketBusController {
    @Autowired
    private ITicketBusService service;


    @PostMapping
    public CreateTicketBusResponse create(@RequestBody CreateTicketBusRequest request) {
        return service.create(request);
    }

    @GetMapping("{idDestination}")
    public List<CreateTicketBusResponse> get(@PathVariable Long idDestination) {
        return service.get(idDestination);
    }

    @GetMapping
    public List<CreateTicketBusResponse> list() {
        return service.list();
    }

    @PutMapping("{id}/{reservation}")
    public CreateTicketBusResponse update(@PathVariable Long id, @PathVariable Long reservation) {
        return service.update(id, reservation);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

