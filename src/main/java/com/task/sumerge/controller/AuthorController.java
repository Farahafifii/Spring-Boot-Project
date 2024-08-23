package com.task.sumerge.controller;

import com.task.sumerge.dto.AuthorDTO;
import com.task.sumerge.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/findByEmail/{email}")
    public AuthorDTO getAuthorByEmail(@PathVariable String email) {
        return authorService.getAuthorByEmail(email);
    }

    @GetMapping("/findById/{id}")
    public AuthorDTO getAuthorById(@PathVariable int id) {
        return authorService.getAuthorById(id);
    }
}
