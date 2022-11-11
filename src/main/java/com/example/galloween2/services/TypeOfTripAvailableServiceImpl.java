package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreateTypeOfTripAvailableRequest;
import com.example.galloween2.controllers.dtos.responses.CreateTypeOfTripAvailableResponse;
import com.example.galloween2.entities.TypeOfTripAvailable;
import com.example.galloween2.repositories.ITypeOfTripAvailableRepository;
import com.example.galloween2.services.interfaces.ITypeOfTripAvailableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeOfTripAvailableServiceImpl implements ITypeOfTripAvailableService {
    @Autowired
    private ITypeOfTripAvailableRepository repository;

    @Override
    public CreateTypeOfTripAvailableResponse create(CreateTypeOfTripAvailableRequest request) {
        TypeOfTripAvailable save = repository.save(from(request));
        return from(save);
    }

    @Override
    public CreateTypeOfTripAvailableResponse get(Long id) {
        TypeOfTripAvailable typeOfTrip = findAndEnsureExist(id);
        return from(typeOfTrip);
    }

    @Override
    public List<CreateTypeOfTripAvailableResponse> list() {
        return repository.findAll().stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public TypeOfTripAvailable findById(Long id) {
        return findAndEnsureExist(id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
    }

    private TypeOfTripAvailable from (CreateTypeOfTripAvailableRequest request){
        TypeOfTripAvailable typeOfTrip = new TypeOfTripAvailable();
        typeOfTrip.setTypeOfTrip(request.getTypeOfTrip());
        return typeOfTrip;
    }

    private CreateTypeOfTripAvailableResponse from(TypeOfTripAvailable typeOfTrip){
        CreateTypeOfTripAvailableResponse response = new CreateTypeOfTripAvailableResponse();
        response.setId(typeOfTrip.getId());
        response.setTypeOfTrip(typeOfTrip.getTypeOfTrip());
        return response;
    }

    private TypeOfTripAvailable findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
