package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreateReservationStatusRequest;
import com.example.galloween2.controllers.dtos.responses.CreateReservationStatusResponse;
import com.example.galloween2.entities.Reservation;
import com.example.galloween2.entities.ReservationStatus;
import com.example.galloween2.repositories.IReservationStatusRepository;
import com.example.galloween2.services.interfaces.IPaymentService;
import com.example.galloween2.services.interfaces.IReservationStatusService;
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

    @Override
    public CreateReservationStatusResponse create(Reservation reservation) {
        ReservationStatus save = repository.save(from(reservation));

        return from(save);
    }

    @Override
    public CreateReservationStatusResponse get(Long id) {
        ReservationStatus reservationStatus = findAndEnsureExist(id);
        return from(reservationStatus);
    }

    @Override
    public List<CreateReservationStatusResponse> list() {
        return repository.findAll().stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public CreateReservationStatusResponse update(Long id, CreateReservationStatusRequest request) {
        ReservationStatus reservationStatus = findAndEnsureExist(id);
        reservationStatus.setStatus(request.getStatus());
        reservationStatus.setPayment(paymentService.findById(request.getPayment()));
        return from(repository.save(reservationStatus));
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
    }

    private ReservationStatus from(Reservation reservation){
        ReservationStatus reservationStatus = new ReservationStatus();
        reservationStatus.setStatus("No pagado");
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