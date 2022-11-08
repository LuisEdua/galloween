package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreateCommentRequest;
import com.example.galloween2.controllers.dtos.request.CreateDestinationTypeOfTripAvailableRequest;
import com.example.galloween2.controllers.dtos.responses.CreateCommentResponse;
import com.example.galloween2.controllers.dtos.responses.CreateDestinationTypeOfTripAvailableResponse;
import com.example.galloween2.controllers.dtos.responses.DestinationTypeOfTripAvailableResponse;
import com.example.galloween2.entities.Comment;
import com.example.galloween2.entities.pivots.DestinationTypeOfTripAvailable;
import com.example.galloween2.entities.projections.DestinationProjection;
import com.example.galloween2.repositories.ICommentRepository;
import com.example.galloween2.repositories.IDestinationTypeOfTripAvailableRepository;
import com.example.galloween2.services.interfaces.ICommentService;
import com.example.galloween2.services.interfaces.IDestinationService;
import com.example.galloween2.services.interfaces.IDestinationTypeOfTripAvailableService;
import com.example.galloween2.services.interfaces.ITypeOfTripAvailableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationTypeOfTripAvailableServiceImpl implements IDestinationTypeOfTripAvailableService {
    @Autowired
    private IDestinationTypeOfTripAvailableRepository repository;
    @Autowired
    private IDestinationService destinationService;
    @Autowired
    private ITypeOfTripAvailableService typeOfTripAvailableService;

    @Override
    public CreateDestinationTypeOfTripAvailableResponse create(CreateDestinationTypeOfTripAvailableRequest request) {
        DestinationTypeOfTripAvailable save = repository.save(from(request));

        return from(save);
    }

    @Override
    public List<DestinationTypeOfTripAvailableResponse> get(String typeOfTrip) {
        List<DestinationProjection> projection = repository.desinationsList(typeOfTrip);
        return projection.stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public List<CreateDestinationTypeOfTripAvailableResponse> list() {
        return repository.findAll().stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public CreateDestinationTypeOfTripAvailableResponse update(Long id, CreateDestinationTypeOfTripAvailableRequest request) {
        DestinationTypeOfTripAvailable destinationTypeOfTripAvailable = findAndEnsureExist(id);

        return from(repository.save(destinationTypeOfTripAvailable));
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
    }

    private DestinationTypeOfTripAvailableResponse from(DestinationProjection projection) {
        DestinationTypeOfTripAvailableResponse response = new DestinationTypeOfTripAvailableResponse();
        response.setCity(projection.getCity());
        response.setId(projection.getId());
        response.setState(projection.getState());
        response.setCountry(projection.getCountry());
        response.setPhotography(projection.getPhotography());
        return response;
    }

    private DestinationTypeOfTripAvailable from(CreateDestinationTypeOfTripAvailableRequest request){
        DestinationTypeOfTripAvailable available = new DestinationTypeOfTripAvailable();
        available.setTypeOfTripAvailable(typeOfTripAvailableService.findById(request.getTypeOfTrip()));
        available.setDestination(destinationService.findById(request.getDestination()));
        return available;
    }

    private CreateDestinationTypeOfTripAvailableResponse from(DestinationTypeOfTripAvailable available){
        CreateDestinationTypeOfTripAvailableResponse response = new CreateDestinationTypeOfTripAvailableResponse();
        response.setId(available.getId());
        response.setTypeOfTrip(available.getTypeOfTripAvailable().getId());
        response.setDestination(available.getDestination().getId());
        return response;
    }

    private DestinationTypeOfTripAvailable findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
