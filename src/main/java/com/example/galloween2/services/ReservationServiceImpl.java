package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreateReservationRequest;
import com.example.galloween2.controllers.dtos.responses.CreateReservationResponse;
import com.example.galloween2.controllers.dtos.responses.CreateReservationStatusResponse;
import com.example.galloween2.entities.Reservation;
import com.example.galloween2.repositories.IReservationRepository;
import com.example.galloween2.services.interfaces.IReservationService;
import com.example.galloween2.services.interfaces.IReservationStatusService;
import com.example.galloween2.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements IReservationService {


    @Autowired
    private IReservationRepository repository;

    @Lazy
    @Autowired
    private IReservationStatusService reservationStatusService;

    @Lazy
    @Autowired
    private IUserService userService;

    @Override
    public CreateReservationResponse create(CreateReservationRequest request, Long user_id) {
        Reservation save = repository.save(from(request, user_id));
        return from(save);
    }

    @Override
    public List<CreateReservationResponse> list(Long id) {
        return repository.findReservationByUserId(id)
                .stream().map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public CreateReservationResponse update(Long id, CreateReservationRequest request) {
        Reservation reservation = findAndEnsureExist(id);
        reservation.setCost(reservation.getCost());
        return from(repository.save(reservation));
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
    }

    @Override
    public Reservation finById(Long id) {
        return findAndEnsureExist(id);
    }

    @Override
    public void userNullAll(Long id, Long userId) {
        repository.userNull(id, userId);
    }


    private Reservation from(CreateReservationRequest request, Long user_id){
        Reservation reservation = new Reservation();
        reservation.setUser(userService.findById(user_id));
        reservation.setReservationDate(request.getReservationDate());
        reservation.setCost(request.getCost());
        return reservation;
    }

    private CreateReservationResponse from(Reservation reservation){
        CreateReservationStatusResponse statusResponse= reservationStatusService.create(reservation);
        CreateReservationResponse response = new CreateReservationResponse();
        response.setId(reservation.getId());
        response.setCost(reservation.getCost());
        response.setReservationDate(reservation.getReservationDate());
        response.setStatus(statusResponse.getStatus());
        response.setUser(reservation.getUser().getFullName());
        return response;
    }

    private Reservation findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Not found"));
    }
}
