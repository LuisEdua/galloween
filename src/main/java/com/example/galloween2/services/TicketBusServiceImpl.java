package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreateTicketBusRequest;
import com.example.galloween2.controllers.dtos.responses.CreateTicketBusResponse;
import com.example.galloween2.entities.TicketBus;
import com.example.galloween2.entities.projections.TicketBusProjection;
import com.example.galloween2.repositories.ITicketBusRepository;
import com.example.galloween2.services.interfaces.IDestinationService;
import com.example.galloween2.services.interfaces.IReservationService;
import com.example.galloween2.services.interfaces.ITicketBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketBusServiceImpl implements ITicketBusService {
    @Autowired
    private ITicketBusRepository repository;
    @Autowired
    private IDestinationService destinationService;
    @Autowired
    private IReservationService reservationService;

    @Override
    public CreateTicketBusResponse create(CreateTicketBusRequest request) {
        TicketBus save = repository.save(from(request));

        return from(save);
    }

    @Override
    public List<CreateTicketBusResponse> get(Long idDestination) {
        return repository.findTicketsByDestination(idDestination)
                .stream().map(this::from).collect(Collectors.toList());
    }

    private CreateTicketBusResponse from(TicketBusProjection ticketBus) {
        CreateTicketBusResponse response = new CreateTicketBusResponse();
        response.setId(ticketBus.getId());
        response.setDepartureDate(ticketBus.getDeparture_date());
        response.setCheckInTime(ticketBus.getCheck_in_time());
        response.setOrigin(ticketBus.getOrigin());
        response.setClass_type(ticketBus.getClass_type());
        response.setSeatNumber(ticketBus.getSeat_number());
        response.setCost(ticketBus.getCost());
        return response;
    }

    @Override
    public List<CreateTicketBusResponse> list() {
        return repository.findAll().stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public CreateTicketBusResponse update(Long id, Long seat) {
        TicketBus ticketBus = findAndEnsureExist(id);
        ticketBus.setSeatNumber(seat);
        return from(repository.save(ticketBus));
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
    }

    @Override
    public List<Long> getCost(Long id){
        return repository.findCostByUserId(id);
    }

    private TicketBus from(CreateTicketBusRequest request){
        TicketBus ticketBus = new TicketBus();
        ticketBus.setCheckInTime(request.getCheckInTime());
        ticketBus.setOrigin(request.getOrigin());
        ticketBus.setCost(request.getCost());
        ticketBus.setDepartureDate(request.getDepartureDate());
        ticketBus.setClass_type(request.getClass_type());
        ticketBus.setSeatNumber(request.getSeatNumber());
        ticketBus.setDestination(destinationService.findById(request.getDestination()));
        return ticketBus;
    }

    private CreateTicketBusResponse from(TicketBus ticketBus){
        CreateTicketBusResponse response = new CreateTicketBusResponse();
        response.setId(ticketBus.getId());
        response.setDepartureDate(ticketBus.getDepartureDate());
        response.setCheckInTime(ticketBus.getCheckInTime());
        response.setOrigin(ticketBus.getOrigin());
        response.setClass_type(ticketBus.getClass_type());
        response.setSeatNumber(ticketBus.getSeatNumber());
        response.setCost(ticketBus.getCost());
        return response;
    }

    private TicketBus findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
