package com.example.galloween2.controllers;

import com.example.galloween2.services.interfaces.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private IFileService service;

    @PostMapping
    public String upload(@RequestParam MultipartFile file) {
        return service.upload(file);
    }
}
