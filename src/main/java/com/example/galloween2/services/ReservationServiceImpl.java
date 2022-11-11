package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreateReservationRequest;
import com.example.galloween2.controllers.dtos.responses.CreateReservationResponse;
import com.example.galloween2.entities.Reservation;
import com.example.galloween2.repositories.IReservationRepository;
import com.example.galloween2.services.interfaces.*;
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

    @Lazy
    @Autowired
    private ITicketBusService busService;

    @Lazy
    @Autowired
    private ITicketAirplaneService airplaneService;

    @Lazy
    @Autowired
    private ITicketCruiseShipService cruiseShipService;

    @Override
    public CreateReservationResponse create(CreateReservationRequest request, Long user_id) {
        Reservation save = repository.save(from(request, user_id));
        reservationStatusService.create(save);
        return from(save);
    }

    @Override
    public List<CreateReservationResponse> list(Long id) {
        return repository.findReservationByUserId(id)
                .stream().map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public Long update(Long id) {
        Long cost = Long.valueOf(0);
        cost += getCostBus(id);
        cost += getCostAirplane(id);
        cost += getCostCruiseShip(id);
        Reservation reservation = findAndEnsureExist(id);
        reservation.setCost(cost);
        repository.save(reservation);
        return cost;
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
    public void userNullAll(Long id) {
        repository.findReservationByUserId(id).stream().map(this::toNull).collect(Collectors.toList());
    }

    private Reservation toNull(Reservation reservation) {
        reservation.setUser(null);
        return repository.save(reservation);
    }

    @Override
    public List<CreateReservationResponse> listAll() {
        return repository.findAll().stream().map(this::from).collect(Collectors.toList());
    }


    private Reservation from(CreateReservationRequest request, Long user_id){
        Reservation reservation = new Reservation();
        reservation.setUser(userService.findById(user_id));
        reservation.setReservationDate(request.getReservationDate());
        return reservation;
    }

    private CreateReservationResponse from(Reservation reservation){
        CreateReservationResponse response = new CreateReservationResponse();
        response.setId(reservation.getId());
        response.setCost(reservation.getCost());
        response.setReservationDate(reservation.getReservationDate());
        response.setStatus(reservationStatusService.findByReservation(reservation.getId()).getStatus());
        response.setUser(reservation.getUser().getFullName());
        return response;
    }

    private Reservation findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Not found"));
    }

    private Long getCostBus(Long id){
        Long cost = Long.valueOf(0);
        List<Long> bus = busService.getCost(id).stream().toList();
        for(int i = 0; i < bus.size(); i++){
            cost += bus.get(i);
        }
        return cost;
    }

    private Long getCostAirplane(Long id){
        Long cost = Long.valueOf(0);
        List<Long> airplane = airplaneService.getCost(id).stream().toList();
        for(int i = 0; i < airplane.size(); i++){
            cost += airplane.get(i);
        }
        return cost;
    }

    private Long getCostCruiseShip(Long id){
        Long cost = Long.valueOf(0);
        List<Long> cruiseShip = cruiseShipService.getCost(id).stream().toList();
        for(int i = 0; i < cruiseShip.size(); i++){
            cost += cruiseShip.get(i);
        }
        return cost;
    }
}
