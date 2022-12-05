package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreateTicketCruiseShipRequest;
import com.example.galloween2.controllers.dtos.responses.CreateTicketCruiseShipResponse;
import com.example.galloween2.entities.TicketCruiseShip;
import com.example.galloween2.entities.projections.TicketAirplaneProjection;
import com.example.galloween2.repositories.ITicketCruiseShipRepository;
import com.example.galloween2.services.interfaces.IDestinationService;
import com.example.galloween2.services.interfaces.IReservationService;
import com.example.galloween2.services.interfaces.ITicketCruiseShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketCruiseShipServiceImpl implements ITicketCruiseShipService {
    @Autowired
    private ITicketCruiseShipRepository repository;
    @Autowired
    private IReservationService reservationService;
    @Autowired
    private IDestinationService destinationService;

    @Override
    public CreateTicketCruiseShipResponse create(CreateTicketCruiseShipRequest request) {
        TicketCruiseShip save = repository.save(from(request));

        return from(save);
    }

    @Override
    public List<CreateTicketCruiseShipResponse> get(Long id) {
        return repository.findTicketsByDestination(id)
                .stream().map(this::from)
                .collect(Collectors.toList());
    }

    private CreateTicketCruiseShipResponse from(TicketAirplaneProjection ticket) {
        CreateTicketCruiseShipResponse response = new CreateTicketCruiseShipResponse();
        response.setOrigin(ticket.getOrigin());
        response.setId(ticket.getId());
        response.setDepartureDate(ticket.getDeparture_date());
        response.setCheckInTime(ticket.getCheck_in_time());
        response.setSeatNumber(ticket.getSeat_number());
        response.setClassType(ticket.getClass_type());
        response.setCost(ticket.getCost());
        return response;
    }

    @Override
    public List<CreateTicketCruiseShipResponse> list() {
        return repository.findAll().stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public CreateTicketCruiseShipResponse update(Long id, Long idReservation) {
        TicketCruiseShip ticketCruiseShip = findAndEnsureExist(id);
        ticketCruiseShip.setReservation(reservationService.finById(idReservation));
        return from(repository.save(ticketCruiseShip));
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
    }

    @Override
    public List<Long> getCost(Long id) {
        return repository.findCostByReservationId(id);
    }

    @Override
    public List<CreateTicketCruiseShipResponse> getTicket(Long id) {
        return repository.findByReservationId(id).stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public void toNull(Long id) {
        repository.findByReservationId(id).stream().map(this::ticketNull).collect(Collectors.toList());
    }

    private TicketCruiseShip ticketNull(TicketCruiseShip ticketCruiseShip) {
        ticketCruiseShip.setReservation(null);
        return repository.save(ticketCruiseShip);
    }

    private TicketCruiseShip from(CreateTicketCruiseShipRequest request){
        TicketCruiseShip ticketCruiseShip = new TicketCruiseShip();
        ticketCruiseShip.setDepartureDate(request.getDepartureDate());
        ticketCruiseShip.setCheckInTime(request.getCheckInTime());
        ticketCruiseShip.setOrigin(request.getOrigin());
        ticketCruiseShip.setClass_type(request.getClassType());
        ticketCruiseShip.setSeatNumber(request.getSeatNumber());
        ticketCruiseShip.setCost(request.getCost());
        ticketCruiseShip.setDestination(destinationService.findById(request.getDestination()));
        return ticketCruiseShip;
    }

    private CreateTicketCruiseShipResponse from(TicketCruiseShip ticketCruiseShip){
        CreateTicketCruiseShipResponse response = new CreateTicketCruiseShipResponse();
        response.setId(ticketCruiseShip.getId());
        response.setDepartureDate(ticketCruiseShip.getDepartureDate());
        response.setCheckInTime(ticketCruiseShip.getCheckInTime());
        response.setOrigin(ticketCruiseShip.getOrigin());
        response.setClassType(ticketCruiseShip.getClass_type());
        response.setSeatNumber(ticketCruiseShip.getSeatNumber());
        response.setCost(ticketCruiseShip.getCost());
        return response;
    }

    private TicketCruiseShip findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
