package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreateTicketAirplaneRequest;
import com.example.galloween2.controllers.dtos.responses.CreateTicketAirplaneResponse;
import com.example.galloween2.entities.TicketAirplane;
import com.example.galloween2.entities.projections.TicketAirplaneProjection;
import com.example.galloween2.repositories.ITicketAirplaneRepository;
import com.example.galloween2.services.interfaces.IDestinationService;
import com.example.galloween2.services.interfaces.IReservationService;
import com.example.galloween2.services.interfaces.ITicketAirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketAirplaneServiceImpl implements ITicketAirplaneService {
    @Autowired
    private ITicketAirplaneRepository repository;
    @Autowired
    private IReservationService reservationService;
    @Autowired
    private IDestinationService destinationService;

    @Override
    public CreateTicketAirplaneResponse create(CreateTicketAirplaneRequest request) {
        TicketAirplane save = repository.save(from(request));

        return from(save);
    }

    @Override
    public List<CreateTicketAirplaneResponse> get(Long id) {
        return repository.findTicketsByDestination(id)
                .stream().map(this::from)
                .collect(Collectors.toList());
    }

    private CreateTicketAirplaneResponse from(TicketAirplaneProjection ticket) {
        CreateTicketAirplaneResponse response = new CreateTicketAirplaneResponse();
        response.setId(ticket.getId());
        response.setClassType(ticket.getClass_type());
        response.setOrigin(ticket.getOrigin());
        response.setCost(ticket.getCost());
        response.setSeatNumber(ticket.getSeat_number());
        response.setDepartureDate(ticket.getDeparture_date());
        response.setCheckInTime(ticket.getCheck_in_time());
        return response;
    }

    @Override
    public List<CreateTicketAirplaneResponse> list() {
        return repository.findAll().stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public CreateTicketAirplaneResponse update(Long id, Long reservationId) {
        TicketAirplane ticketAirplane = findAndEnsureExist(id);
        ticketAirplane.setReservation(reservationService.finById(reservationId));
        return from(repository.save(ticketAirplane));
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
    }

    @Override
    public List<Long> getCost(Long id) {
        return repository.findCostByReservation(id);
    }

    @Override
    public List<CreateTicketAirplaneResponse> getTicket(Long id) {
        return repository.findByReservationId(id).stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public void toNull(Long id) {
        repository.findByReservationId(id).stream().map(this::reservationNull).collect(Collectors.toList());
    }

    private TicketAirplane reservationNull(TicketAirplane ticketAirplane) {
        ticketAirplane.setReservation(null);
        return repository.save(ticketAirplane);
    }


    private TicketAirplane from(CreateTicketAirplaneRequest request){
        TicketAirplane ticketAirplane = new TicketAirplane();
        ticketAirplane.setDepartureDate(request.getDepartureDate());
        ticketAirplane.setCheckInTime(request.getCheckInTime());
        ticketAirplane.setOrigin(request.getOrigin());
        ticketAirplane.setClass_type(request.getClassType());
        ticketAirplane.setSeatNumber(request.getSeatNumber());
        ticketAirplane.setCost(request.getCost());
        ticketAirplane.setDestination(destinationService.findById(request.getDestination()));
        return ticketAirplane;
    }

    private CreateTicketAirplaneResponse from(TicketAirplane ticketAirplane){
        CreateTicketAirplaneResponse response = new CreateTicketAirplaneResponse();
        response.setId(ticketAirplane.getId());
        response.setDepartureDate(ticketAirplane.getDepartureDate());
        response.setCheckInTime(ticketAirplane.getCheckInTime());
        response.setOrigin(ticketAirplane.getOrigin());
        response.setClassType(ticketAirplane.getClass_type());
        response.setSeatNumber(ticketAirplane.getSeatNumber());
        response.setCost(ticketAirplane.getCost());
        return response;
    }

    private TicketAirplane findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
