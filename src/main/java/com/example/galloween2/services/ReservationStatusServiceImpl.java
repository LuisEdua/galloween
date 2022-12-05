package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreateReservationStatusRequest;
import com.example.galloween2.controllers.dtos.responses.CreateReservationStatusResponse;
import com.example.galloween2.entities.Reservation;
import com.example.galloween2.entities.ReservationStatus;
import com.example.galloween2.repositories.IReservationStatusRepository;
import com.example.galloween2.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationStatusServiceImpl implements IReservationStatusService {
    @Autowired
    private IReservationStatusRepository repository;

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private ITicketAirplaneService ticketAirplaneService;

    @Autowired
    private ITicketCruiseShipService cruiseShipService;

    @Autowired
    private ITicketBusService busService;

    @Override
    public CreateReservationStatusResponse create(Reservation reservation) {
        ReservationStatus save = repository.save(from(reservation));

        return from(save);
    }

    @Override
    public CreateReservationStatusResponse findByReservation(Long id) {
        return from(repository.findByReservationId(id));
    }

    @Override
    public CreateReservationStatusResponse update(Long id, CreateReservationStatusRequest request) {
        ReservationStatus reservationStatus = repository.findByReservationId(id);
        reservationStatus.setStatus(request.getStatus());
        reservationStatus.setPayment(paymentService.findById(request.getPayment()));
        return from(repository.save(reservationStatus));
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
    }

    @Override
    public void nullPayment(Long id){
        List<ReservationStatus> status = repository.findByPaymentId(id);
        status.stream().map(this::toNull).collect(Collectors.toList());
    }

    @Override
    public CreateReservationStatusResponse get(Long idReservation) {
        return from(repository.findByReservationId(idReservation));
    }

    @Override
    public void updateAirplane(Long id) {
        repository.findByReservationId(id).setStatus("Cancelled");
        ticketAirplaneService.toNull(id);
    }

    @Override
    public void updateCruiceShip(Long id) {
        repository.findByReservationId(id).setStatus("Cancelled");
        cruiseShipService.toNull(id);
    }

    @Override
    public void updateBus(Long id) {
        repository.findByReservationId(id).setStatus("Cancelled");
        busService.toNull(id);
    }

    private ReservationStatus toNull(ReservationStatus status) {
        status.setPayment(null);
        return repository.save(status);
    }

    private ReservationStatus from(Reservation reservation){
        ReservationStatus reservationStatus = new ReservationStatus();
        reservationStatus.setStatus("Not payed");
        reservationStatus.setReservation(reservation);
        return reservationStatus;
    }

    private CreateReservationStatusResponse from(ReservationStatus reservationStatus){
        CreateReservationStatusResponse response = new CreateReservationStatusResponse();
        response.setId(reservationStatus.getId());
        response.setStatus(reservationStatus.getStatus());
        return response;
    }

    private ReservationStatus findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
