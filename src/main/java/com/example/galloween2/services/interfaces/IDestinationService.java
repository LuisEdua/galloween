package com.example.galloween2.services.interfaces;

import com.example.galloween2.controllers.dtos.request.CreateDestinationRequest;
import com.example.galloween2.controllers.dtos.responses.CreateDestinationResponse;
import com.example.galloween2.entities.Destination;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IDestinationService {

//}
// CreateDestinationResponse create(CreateDestinationRequest request);
    CreateDestinationResponse create(String city, String state, String country, MultipartFile file);

    CreateDestinationResponse get(Long id);

    List<CreateDestinationResponse> list();

    CreateDestinationResponse update(Long id, CreateDestinationRequest request);

    void delete(Long id);

    Destination findById(Long id);
}
