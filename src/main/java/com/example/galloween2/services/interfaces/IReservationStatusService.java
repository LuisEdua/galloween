package com.example.galloween2.services.interfaces;

import com.example.galloween2.controllers.dtos.request.CreateReservationStatusRequest;
import com.example.galloween2.controllers.dtos.responses.CreateReservationStatusResponse;
import com.example.galloween2.entities.Reservation;
import com.example.galloween2.entities.ReservationStatus;

import java.util.List;

public interface IReservationStatusService {
    CreateReservationStatusResponse create(Reservation reservation);

    CreateReservationStatusResponse findByReservation(Long id);

    List<CreateReservationStatusResponse> list();

    CreateReservationStatusResponse update(Long id, CreateReservationStatusRequest request);

    void delete(Long id);
}
