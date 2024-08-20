package com.task.sumerge.service;

import com.task.sumerge.dto.AuthorDTO;
import com.task.sumerge.mapper.AuthorMapper;
import com.task.sumerge.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepository;

    public AuthorDTO getAuthorByEmail(String email) {
        return AuthorMapper.INSTANCE.toDTO(authorRepository.findByEmail(email));

    }
}
