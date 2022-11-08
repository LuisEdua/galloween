package com.example.galloween2.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    String upload(MultipartFile multipartFile);
}
