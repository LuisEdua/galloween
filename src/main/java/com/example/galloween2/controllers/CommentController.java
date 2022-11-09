package com.example.galloween2.controllers;

import com.example.galloween2.controllers.dtos.request.CreateCommentRequest;
import com.example.galloween2.controllers.dtos.responses.CommentResponse;
import com.example.galloween2.services.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private ICommentService service;

    @PostMapping("{user_id}")
    public CommentResponse create(@RequestBody CreateCommentRequest request, @PathVariable Long user_id) {
        return service.create(request, user_id);
    }

    @GetMapping
    public List<CommentResponse> list() {
        return service.list();
    }

    @PutMapping("{id}")
    public CommentResponse update(@PathVariable Long id, @RequestBody CreateCommentRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

