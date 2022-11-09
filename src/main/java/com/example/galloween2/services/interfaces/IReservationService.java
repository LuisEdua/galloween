package com.example.galloween2.services.interfaces;

import com.example.galloween2.controllers.dtos.request.CreateReservationRequest;
import com.example.galloween2.controllers.dtos.responses.CreateReservationResponse;
import com.example.galloween2.entities.Reservation;

import java.util.List;

public interface IReservationService {
    CreateReservationResponse create(CreateReservationRequest request, Long user_id);

    List<CreateReservationResponse> list(Long id);

    CreateReservationResponse update(Long id, CreateReservationRequest request);

    void delete(Long id);

    Reservation finById(Long id);

    void userNullAll(Long id, Long idUser);
}
