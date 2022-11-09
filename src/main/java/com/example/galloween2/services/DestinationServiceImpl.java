package com.example.galloween2.services;

import com.example.galloween2.controllers.dtos.request.CreateDestinationRequest;
import com.example.galloween2.controllers.dtos.responses.CreateDestinationResponse;
import com.example.galloween2.entities.Destination;
import com.example.galloween2.repositories.IDestinationRepository;
import com.example.galloween2.services.interfaces.IDestinationService;
import com.example.galloween2.services.interfaces.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DestinationServiceImpl implements IDestinationService {
    @Autowired
    private IDestinationRepository repository;

    @Lazy
    @Autowired
    private IFileService fileService;

    /*@Override
    public CreateDestinationResponse create(CreateDestinationRequest request) {
        Destination save = repository.save(from(request));

        return from(save);
    }*/
    public CreateDestinationResponse create(String city, String state, String country, MultipartFile file) {
        Destination save = repository.save(from(city, state, country, file));

        return from(save);
    }

    @Override
    public CreateDestinationResponse get(Long id) {
        Destination destination = findAndEnsureExist(id);
        return from(destination);
    }

    @Override
    public List<CreateDestinationResponse> list() {
        return repository.findAll().stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public CreateDestinationResponse update(Long id, CreateDestinationRequest request) {
        Destination destination = findAndEnsureExist(id);
        destination.setState(request.getState());
        destination.setCountry(request.getCountry());
        return from(repository.save(destination));
    }

    @Override
    public void delete(Long id) {
        repository.delete(findAndEnsureExist(id));
    }

    @Override
    public Destination findById(Long id) {
        return findAndEnsureExist(id);
    }

    /*private Destination from(CreateDestinationRequest request) {
        Destination destination = new Destination();
        destination.setCity(request.getCity());
        destination.setState(request.getState());
        destination.setCountry(request.getCountry());
        destination.setPhotography(fileService.upload(request.getPicture()));
        return destination;
    }*/

    private Destination from(String city, String state, String country, MultipartFile file) {
        Destination destination = new Destination();
        destination.setCity(city);
        destination.setState(state);
        destination.setCountry(country);
        destination.setPhotography(fileService.upload(file));
        return destination;
    }

    private CreateDestinationResponse from(Destination destination){
        CreateDestinationResponse response = new CreateDestinationResponse();
        response.setCity(destination.getCity());
        response.setId(destination.getId());
        response.setState(destination.getState());
        response.setCountry(destination.getCountry());
        response.setPhotography(destination.getPhotography());
        return response;
    }

    private Destination findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
